package com.IT0033.weddingApp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "event-client", url = "${services.event-service.url}")
public interface EventClientIT0033<EventDtoDIT0033> {

    @GetMapping("/{eventId}")
    EventDtoDIT0033 getEventById(@PathVariable("eventId") Long eventId);
}
