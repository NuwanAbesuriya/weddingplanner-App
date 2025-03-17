package com.IT0033.weddingApp.mapper;

import com.IT0033.weddingApp.dto.VendorDTOIT0033;
import com.IT0033.weddingApp.entity.VendorIT0033;

public class VendorMapperIT0033 {

    public static VendorDTOIT0033 mapToVendorDto(VendorIT0033 vendorIT0033){
        return new VendorDTOIT0033(
                vendorIT0033.getId(),
                vendorIT0033.getName(),
                vendorIT0033.getVendorType(),
                vendorIT0033.getNotes(),
                vendorIT0033.getContactDetails(),
                vendorIT0033.getPriceRange(),
                vendorIT0033.getAvailable()

        );
    }

    public static VendorIT0033 mapToVendorIT0033(VendorDTOIT0033 vendorDTOIT0033){
        return new VendorIT0033(
                vendorDTOIT0033.getId(),
                vendorDTOIT0033.getName(),
                vendorDTOIT0033.getVendorType(),
                vendorDTOIT0033.getNotes(),
                vendorDTOIT0033.getContactDetails(),
                vendorDTOIT0033.getPriceRange(),
                vendorDTOIT0033.getAvailable()
        );
    }


}
