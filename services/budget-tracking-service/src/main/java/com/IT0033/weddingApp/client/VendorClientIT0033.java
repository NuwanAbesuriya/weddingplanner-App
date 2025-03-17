package com.IT0033.weddingApp.client;

import com.IT0033.weddingApp.dto.VendorDTOIT0033;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "vendor-client", url = "http://localhost:8070")
public interface VendorClientIT0033 {

    @GetMapping("/api/vendors/{vendorId}")
    VendorDTOIT0033 getVendorById(@PathVariable("vendorId") Long vendorId);

    @GetMapping("/api/vendors/{vendorId}/availability")
    void checkVendorAvailability(@PathVariable("vendorId") Long vendorId);
}