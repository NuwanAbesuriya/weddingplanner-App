package com.IT0033.weddingApp.mapper;

import com.IT0033.weddingApp.dto.GuestRequestDTOIT0033;
import com.IT0033.weddingApp.dto.GuestResponseDTOIT0033;
import com.IT0033.weddingApp.entity.GuestIT0033;

public class GuestMapperIT0033 {

    public static GuestIT0033 mapToGuestEntity(GuestRequestDTOIT0033 guestRequestDTO) {
        if (guestRequestDTO == null) {
            return null; // Safeguard against null input
        }
        return GuestIT0033.builder()
                .eventId(guestRequestDTO.getEventId())
                .name(guestRequestDTO.getName())
                .email(guestRequestDTO.getEmail())
                .rsvpStatus(guestRequestDTO.getRsvpStatus())
                .invitationSent(guestRequestDTO.isInvitationSent())
                .build();
    }

    public static GuestResponseDTOIT0033 mapToGuestResponseDTO(GuestIT0033 guestIT0033) {
        if (guestIT0033 == null) {
            return null; // Safeguard against null input
        }
        return GuestResponseDTOIT0033.builder()
                .id(guestIT0033.getId())
                .eventId(guestIT0033.getEventId())
                .name(guestIT0033.getName())
                .email(guestIT0033.getEmail())
                .rsvpStatus(guestIT0033.getRsvpStatus())
                .invitationSent(guestIT0033.isInvitationSent())
                .build();
    }
}
