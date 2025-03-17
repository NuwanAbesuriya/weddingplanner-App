package com.IT0033.weddingApp.service;

import com.IT0033.weddingApp.client.GuestClientIT0033;
import com.IT0033.weddingApp.client.GuestDtoIT0033;
import com.IT0033.weddingApp.dto.EventDtoIT0033;
import com.IT0033.weddingApp.dto.TaskResponseDTOIT0033;
import com.IT0033.weddingApp.entity.EventIT0033;
import com.IT0033.weddingApp.exception.ResourceNotFoundException;
import com.IT0033.weddingApp.mapper.EventMapperIT0033;
import com.IT0033.weddingApp.repository.EventRepositoryIT0033;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EventServiceIT0033 {

    private EventRepositoryIT0033 eventRepositoryIT0033;
    private GuestClientIT0033 guestClientIT0033;
    private final WebClient vendorWebClient;
    private final WebClient taskServiceWebClient;

    public EventDtoIT0033 createEvent(EventDtoIT0033 eventDtoIT0033) {
        // Map DTO to Entity
        EventIT0033 eventIT0033 = EventMapperIT0033.mapToEventDBI0019(eventDtoIT0033);

        // Validate Vendor Availability
        validateVendorAvailability(eventDtoIT0033.getVendorId());

        // Save the event to the database
        EventIT0033 savedEvent = eventRepositoryIT0033.save(eventIT0033);

        // Map the saved entity to DTO with empty guests and tasks (no guests or tasks are created during event creation)
        return EventMapperIT0033.mapToEventDto(savedEvent, List.of(), List.of());
    }


    // Get event by ID with specific guests for that event
    public EventDtoIT0033 getEventById(Long eventId) {
        EventIT0033 event = eventRepositoryIT0033.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event not found with ID: " + eventId));

        // Fetch guests from Guest Service
        List<GuestDtoIT0033> guests = guestClientIT0033.getGuestsByEventId(eventId);

        // Fetch tasks from Task Service
        List<TaskResponseDTOIT0033> tasks = fetchTasksByEventId(eventId);

        // Map entity to DTO with guests and tasks
        return EventMapperIT0033.mapToEventDto(event, guests, tasks);
    }


    // Get all events with specific guest lists
    public List<EventDtoIT0033> getAllEvents() {
        // Fetch all events
        List<EventIT0033> events = eventRepositoryIT0033.findAll();

        // Map events to DTOs and enrich with guest information
        return events.stream()
                .map(event -> {
                    EventDtoIT0033 eventDto = EventMapperIT0033.mapToEventDto(event,List.of(), List.of());

                    // Fetch guests specific to this event
                    List<GuestDtoIT0033> guests = guestClientIT0033.getGuestsByEventId(event.getEventId()); // Call Feign Client to get event-specific guests
                    eventDto.setGuests(guests);

                    return eventDto;
                })
                .collect(Collectors.toList());
    }
    public EventDtoIT0033 updateEvent(Long eventId, EventDtoIT0033 updateEvent) {
        EventIT0033 event = eventRepositoryIT0033.findById(eventId)
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exist with given ID: " + eventId));

        // Validate Vendor Availability if vendorId is being updated
        if (updateEvent.getVendorId() != null && !updateEvent.getVendorId().equals(event.getVendorId())) {
            try {
                vendorWebClient.get()
                        .uri("/{vendorId}/availability", updateEvent.getVendorId())
                        .retrieve()
                        .toBodilessEntity()
                        .block(); // Synchronous call

                // Vendor is available
                event.setVendorId(updateEvent.getVendorId());
            } catch (WebClientResponseException e) {
                if (e.getStatusCode() == HttpStatus.CONFLICT) {
                    throw new IllegalStateException("Vendor with ID " + updateEvent.getVendorId() + " is not available.");
                } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    throw new ResourceNotFoundException("Vendor with ID " + updateEvent.getVendorId() + " not found.");
                } else {
                    throw new RuntimeException("Failed to validate vendor availability.", e);
                }
            }
        }

        event.setEventName(updateEvent.getEventName());
        event.setEventDate(updateEvent.getEventDate());
        event.setVenue(updateEvent.getVenue());
        event.setEventType(updateEvent.getEventType());

        EventIT0033 updatedEventObj = eventRepositoryIT0033.save(event);

        return EventMapperIT0033.mapToEventDto(updatedEventObj,List.of(), List.of());
    }


    public void deleteEvent(Long eventId){
        EventIT0033 event= eventRepositoryIT0033.findById(eventId)
                .orElseThrow(()->new ResourceNotFoundException("Event is not exists with given id :" + eventId));

        eventRepositoryIT0033.deleteById(eventId);
    }

    private List<TaskResponseDTOIT0033> fetchTasksByEventId(Long eventId) {
        try {
            return taskServiceWebClient.get()
                    .uri("/tasks/event/{eventId}", eventId)
                    .retrieve()
                    .bodyToFlux(TaskResponseDTOIT0033.class)
                    .collectList()
                    .block(); // Synchronous call
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException("Tasks not found for Event ID: " + eventId);
            } else {
                throw new RuntimeException("Failed to fetch tasks for Event ID: " + eventId, e);
            }
        }
    }
    private void validateVendorAvailability(Long vendorId) {
        try {
            vendorWebClient.get()
                    .uri("/{vendorId}/availability", vendorId)
                    .retrieve()
                    .toBodilessEntity()
                    .block(); // Synchronous call
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.CONFLICT) {
                throw new IllegalStateException("Vendor with ID " + vendorId + " is not available.");
            } else if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new ResourceNotFoundException("Vendor with ID " + vendorId + " not found.");
            } else {
                throw new RuntimeException("Failed to validate vendor availability.", e);
            }
        }
    }

}
