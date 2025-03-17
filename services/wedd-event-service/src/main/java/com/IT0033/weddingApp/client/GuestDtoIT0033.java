package com.IT0033.weddingApp.client;

import com.IT0033.weddingApp.entity.RsvpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GuestDtoIT0033 {
    private Long id;
    private Long eventId;
    private String name;
    private String email;
    private RsvpStatus rsvpStatus;
    private boolean invitationSent;
}
