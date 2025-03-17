package com.IT0033.weddingApp.mapper;

import com.IT0033.weddingApp.client.GuestDtoIT0033;
import com.IT0033.weddingApp.dto.EventDtoIT0033;
import com.IT0033.weddingApp.dto.TaskResponseDTOIT0033;
import com.IT0033.weddingApp.entity.EventIT0033;

import java.util.List;

public class EventMapperIT0033 {

    public static EventDtoIT0033 mapToEventDto(EventIT0033 eventIT0033,
                                               List<GuestDtoIT0033> guests,
                                               List<TaskResponseDTOIT0033> tasks) {
        return new EventDtoIT0033(
                eventIT0033.getEventId(),
                eventIT0033.getEventName(),
                eventIT0033.getEventDate(),
                eventIT0033.getVenue(),
                eventIT0033.getEventType(),
                eventIT0033.getVendorId(),
                guests,
                tasks
        );
    }

    public static EventIT0033 mapToEventDBI0019(EventDtoIT0033 eventDtoIT0033) {
        return new EventIT0033(
                eventDtoIT0033.getEventId(),
                eventDtoIT0033.getEventName(),
                eventDtoIT0033.getEventDate(),
                eventDtoIT0033.getVenue(),
                eventDtoIT0033.getEventType(),
                eventDtoIT0033.getVendorId()
        );
    }
}
