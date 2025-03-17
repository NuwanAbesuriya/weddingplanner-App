package com.IT0033.weddingApp.repository;


import com.IT0033.weddingApp.entity.GuestIT0033;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface GuestRepositoryIT0033 extends JpaRepository<GuestIT0033,Long> {
    List<GuestIT0033> findByEventId(Long eventId);
}
