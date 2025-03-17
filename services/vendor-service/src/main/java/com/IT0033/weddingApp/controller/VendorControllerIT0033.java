package com.IT0033.weddingApp.controller;

import com.IT0033.weddingApp.service.VendorServiceIT0033;
import com.IT0033.weddingApp.dto.VendorDTOIT0033;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorControllerIT0033 {

    private final VendorServiceIT0033 vendorService;


    @GetMapping("/{vendorId}/availability")
    public ResponseEntity<Void> checkVendorAvailability(@PathVariable Long vendorId) {
        try {
            System.out.println("Checking availability for vendor: " + vendorId);
            boolean isAvailable = vendorService.isVendorAvailable(vendorId);
            if (isAvailable) {
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.status(409).build(); // Conflict
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<VendorDTOIT0033> createVendor(@Valid @RequestBody VendorDTOIT0033 vendorDTO) {
        VendorDTOIT0033 createdVendor = vendorService.createVendor(vendorDTO);
        return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDTOIT0033> getVendorById(@PathVariable Long id) {
        VendorDTOIT0033 vendorDTO = vendorService.getVendorById(id);
        return ResponseEntity.ok(vendorDTO);
    }

    @GetMapping
    public ResponseEntity<List<VendorDTOIT0033>> getAllVendors() {
        List<VendorDTOIT0033> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDTOIT0033> updateVendor(@PathVariable Long id,
                                                        @Valid @RequestBody VendorDTOIT0033 vendorDTO) {
        VendorDTOIT0033 updatedVendor = vendorService.updateVendor(id, vendorDTO);
        return ResponseEntity.ok(updatedVendor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}
