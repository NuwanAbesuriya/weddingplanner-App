package com.IT0033.weddingApp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(value = "guest-service",url = "http://localhost:8050")
public interface GuestClientIT0033 {

    @GetMapping("/api/guests")
    List<GuestDtoIT0033> getAllGuests();

    // New method to get guests by eventId
    @GetMapping("/api/guests/{eventId}")
    List<GuestDtoIT0033> getGuestsByEventId(@PathVariable("eventId") Long eventId);
}
