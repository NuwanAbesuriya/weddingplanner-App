package com.IT0033.weddingApp.dto;

import com.IT0033.weddingApp.entity.RsvpStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestRequestDTOIT0033 {
    private Long eventId;
    private String name;
    private String email;
    private RsvpStatus rsvpStatus;
    private boolean invitationSent;
}
