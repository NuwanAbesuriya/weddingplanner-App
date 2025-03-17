package com.IT0033.weddingApp.controller;

import com.IT0033.weddingApp.dto.GuestRequestDTOIT0033;
import com.IT0033.weddingApp.dto.GuestResponseDTOIT0033;
import com.IT0033.weddingApp.service.GuestServiceIT0033;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestControllerIT0033 {

    private final GuestServiceIT0033 guestServiceIT0033;

    @PostMapping
    public ResponseEntity<GuestResponseDTOIT0033> addGuest(@RequestBody GuestRequestDTOIT0033 guestDTO) {
        return ResponseEntity.ok(guestServiceIT0033.addGuest(guestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuestResponseDTOIT0033> updateGuest(
            @PathVariable Long id,
            @RequestBody GuestRequestDTOIT0033 guestDTO) {
        return ResponseEntity.ok(guestServiceIT0033.updateGuest(id, guestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuest(@PathVariable Long id) {
        guestServiceIT0033.deleteGuest(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{eventId}")
    public ResponseEntity<List<GuestResponseDTOIT0033>> getGuestsByEventId(@PathVariable("eventId") Long eventId) {
        List<GuestResponseDTOIT0033> guests = guestServiceIT0033.getGuestsByEventId(eventId);
        return ResponseEntity.ok(guests);
    }
    @PostMapping("/{id}/send-rsvp")
    public ResponseEntity<Void> sendRsvpRequest(@PathVariable Long id) {
        guestServiceIT0033.sendRsvpRequest(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/track")
    public ResponseEntity<List<GuestResponseDTOIT0033>> trackRsvpResponses(@RequestParam Long eventId) {
        return ResponseEntity.ok(guestServiceIT0033.trackRsvpResponses(eventId));
    }

}
