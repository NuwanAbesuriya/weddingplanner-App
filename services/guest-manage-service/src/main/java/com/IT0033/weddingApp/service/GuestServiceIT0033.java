package com.IT0033.weddingApp.service;

import com.IT0033.weddingApp.dto.GuestRequestDTOIT0033;
import com.IT0033.weddingApp.dto.GuestResponseDTOIT0033;
import com.IT0033.weddingApp.repository.GuestRepositoryIT0033;
import com.IT0033.weddingApp.entity.GuestIT0033;
import com.IT0033.weddingApp.entity.RsvpStatus;
import com.IT0033.weddingApp.mapper.GuestMapperIT0033;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestServiceIT0033 {

    private final GuestRepositoryIT0033 guestRepositoryIT0033;

    public GuestResponseDTOIT0033 addGuest(GuestRequestDTOIT0033 guestDTO) {
        GuestIT0033 guest = GuestMapperIT0033.mapToGuestEntity(guestDTO);
        guest.setRsvpStatus(RsvpStatus.PENDING); // Default status
        guest.setInvitationSent(false);
        GuestIT0033 savedGuest = guestRepositoryIT0033.save(guest);
        return GuestMapperIT0033.mapToGuestResponseDTO(savedGuest);
    }

    public GuestResponseDTOIT0033 updateGuest(Long id, GuestRequestDTOIT0033 guestDTO) {
        GuestIT0033 guest = guestRepositoryIT0033.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));

        guest.setName(guestDTO.getName());
        guest.setEmail(guestDTO.getEmail());
        guest.setRsvpStatus(guestDTO.getRsvpStatus());
        guest.setInvitationSent(guestDTO.isInvitationSent());

        GuestIT0033 updatedGuest = guestRepositoryIT0033.save(guest);
        return GuestMapperIT0033.mapToGuestResponseDTO(updatedGuest);
    }

    public void deleteGuest(Long id) {
        GuestIT0033 guest = guestRepositoryIT0033.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));
        guestRepositoryIT0033.delete(guest);
    }

    public void sendRsvpRequest(Long id) {
        GuestIT0033 guest = guestRepositoryIT0033.findById(id)
                .orElseThrow(() -> new RuntimeException("Guest not found with id: " + id));
        guest.setInvitationSent(true);
        guestRepositoryIT0033.save(guest);
        // Here you can add email sending logic if required.
    }


    public List<GuestResponseDTOIT0033> trackRsvpResponses(Long eventId) {
        List<GuestIT0033> guests = guestRepositoryIT0033.findAll()
                .stream()
                .filter(guest -> guest.getEventId().equals(eventId))
                .toList();
        return guests.stream()
                .map(GuestMapperIT0033::mapToGuestResponseDTO)
                .collect(Collectors.toList());
    }
    public List<GuestResponseDTOIT0033> getGuestsByEventId(Long eventId) {
        List<GuestIT0033> guests = guestRepositoryIT0033.findByEventId(eventId); // Assuming a method in repository to fetch guests by eventId
        return guests.stream()
                .map(guest -> new GuestResponseDTOIT0033(
                        guest.getId(),
                        guest.getEventId(),
                        guest.getName(),
                        guest.getEmail(),
                        guest.getRsvpStatus(),
                        guest.isInvitationSent()
                ))
                .collect(Collectors.toList());
    }

}














