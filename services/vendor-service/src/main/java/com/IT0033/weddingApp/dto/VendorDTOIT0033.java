package com.IT0033.weddingApp.dto;

import com.IT0033.weddingApp.entity.VendorType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorDTOIT0033 {

    private Long id;

    @NotBlank(message = "Vendor name is mandatory")
    @Size(max = 100, message = "Vendor name must be less than 100 characters")
    private String name;

    @NotNull(message = "Vendor type is mandatory")
    private VendorType vendorType;

    @Size(max = 500, message = "Notes must be less than 500 characters")
    private String notes;

    @NotBlank(message = "Contact Details are mandatory")
    @Size(max = 500, message = "Contact Details must be less than 500 characters")
    private String contactDetails;

    @NotBlank(message = "Price range is mandatory")
    @Pattern(
            regexp = "^[0-9]+-[0-9]+$",
            message = "Price range must be in the format 'min-max' with non-negative integers"
    )

    private String priceRange;

    @NotNull(message = "Vendor availability status is mandatory")
    private Boolean available;
}
