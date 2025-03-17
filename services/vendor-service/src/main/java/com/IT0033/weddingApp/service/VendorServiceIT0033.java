package com.IT0033.weddingApp.service;

import com.IT0033.weddingApp.dto.VendorDTOIT0033;
import com.IT0033.weddingApp.entity.VendorIT0033;
import com.IT0033.weddingApp.exception.ResourceNotFoundException;
import com.IT0033.weddingApp.mapper.VendorMapperIT0033;
import com.IT0033.weddingApp.repository.VendorRepositoryIT0033;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class VendorServiceIT0033 {

    private final VendorRepositoryIT0033 vendorRepository;

    public VendorDTOIT0033 createVendor(VendorDTOIT0033 vendorDTO) {
        log.info("Creating vendor with name: {}", vendorDTO.getName());
        VendorIT0033 vendorEntity = VendorMapperIT0033.mapToVendorIT0033(vendorDTO);
        VendorIT0033 savedVendor = vendorRepository.save(vendorEntity);
        return VendorMapperIT0033.mapToVendorDto(savedVendor);
    }

    public boolean isVendorAvailable(Long vendorId) {
        System.out.println("Vendor ID: " + vendorId);
        boolean result = vendorRepository.existsByIdAndAvailable(vendorId, true);
        System.out.println("Is vendor available: " + result);
        return result;
    }


    @Transactional(readOnly = true) // ✅ Now Recognized
    public VendorDTOIT0033 getVendorById(Long id) {
        log.info("Fetching vendor with id: {}", id);
        VendorIT0033 vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));
        return VendorMapperIT0033.mapToVendorDto(vendor);
    }

    @Transactional(readOnly = true) // ✅ Now Recognized
    public List<VendorDTOIT0033> getAllVendors() {
        log.info("Fetching all vendors");
        return vendorRepository.findAll()
                .stream()
                .map(VendorMapperIT0033::mapToVendorDto)
                .collect(Collectors.toList());
    }


    public VendorDTOIT0033 updateVendor(Long id, VendorDTOIT0033 vendorDTO) {
        log.info("Updating vendor with id: {}", id);
        VendorIT0033 existingVendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));

        existingVendor.setName(vendorDTO.getName());
        existingVendor.setVendorType(vendorDTO.getVendorType());
        existingVendor.setNotes(vendorDTO.getNotes());
        existingVendor.setContactDetails(vendorDTO.getContactDetails());
        existingVendor.setPriceRange(vendorDTO.getPriceRange());
        existingVendor.setAvailable(vendorDTO.getAvailable()); // Update availability status

        VendorIT0033 updatedVendor = vendorRepository.save(existingVendor);
        return VendorMapperIT0033.mapToVendorDto(updatedVendor);
    }
    public void deleteVendor(Long id) {
        log.info("Deleting vendor with id: {}", id);
        VendorIT0033 vendor = vendorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor not found with id: " + id));
        vendorRepository.delete(vendor);
    }
}
