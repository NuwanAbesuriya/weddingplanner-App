package com.IT0033.weddingApp.dto;

import com.IT0033.weddingApp.client.GuestDtoIT0033;
import com.IT0033.weddingApp.entity.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDtoIT0033 {
    private Long eventId;
    private String eventName;
    private Date eventDate;
    private String venue;
    private EventType eventType;
    private Long vendorId;
    private List<GuestDtoIT0033> guests;
    private List<TaskResponseDTOIT0033> tasks;

    // Add a constructor with 5 parameters excluding the guests
    public EventDtoIT0033(Long eventId, String eventName, Date eventDate, String venue, EventType eventType) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.eventType = eventType;
    }
}
