package com.IT0033.weddingApp.repository;

import com.IT0033.weddingApp.entity.VendorIT0033;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepositoryIT0033 extends JpaRepository<VendorIT0033, Long> {
    boolean existsByIdAndAvailable(Long id, Boolean available);
}
