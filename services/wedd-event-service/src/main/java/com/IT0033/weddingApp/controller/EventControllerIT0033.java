package com.IT0033.weddingApp.controller;

import com.IT0033.weddingApp.dto.EventDtoIT0033;
import com.IT0033.weddingApp.service.EventServiceIT0033;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/event")
public class EventControllerIT0033 {

    private EventServiceIT0033 eventServiceIT0033;

    @PostMapping
    public ResponseEntity<EventDtoIT0033>createEvent(@RequestBody EventDtoIT0033 eventDtoIT0033){
        EventDtoIT0033 savedEvent= eventServiceIT0033.createEvent(eventDtoIT0033);
        return new ResponseEntity<>(savedEvent, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<EventDtoIT0033> getEventById(@PathVariable("id") Long eventId){
        EventDtoIT0033 eventDtoIT0033 = eventServiceIT0033.getEventById(eventId);
        return ResponseEntity.ok(eventDtoIT0033);
    }

    @GetMapping
    public ResponseEntity<List<EventDtoIT0033>> getAllEvents(){
        List<EventDtoIT0033> events= eventServiceIT0033.getAllEvents();
        return ResponseEntity.ok(events);
    }

    public ResponseEntity<EventDtoIT0033> updateEvent(@PathVariable("id") Long eventId,
                                                      @RequestBody EventDtoIT0033 updateEvent){
        EventDtoIT0033 eventDtoIT0033 = eventServiceIT0033.updateEvent(eventId,updateEvent);
        return ResponseEntity.ok(eventDtoIT0033);
    }

    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long eventId){
        eventServiceIT0033.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted successfully!");
    }
}
