package com.IT0033.weddingApp.dto;

import com.IT0033.weddingApp.entity.RsvpStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestResponseDTOIT0033 {
    private Long id;
    private Long eventId;
    private String name;
    private String email;
    private RsvpStatus rsvpStatus;
    private boolean invitationSent;
}
