package com.IT0033.weddingApp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vendors")
public class VendorIT0033 {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @NotBlank(message = "Vendor name is mandatory")
    @Size(max = 100, message = "Vendor name must be less than 100 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull(message = "Vendor type is mandatory")
    @Enumerated(EnumType.STRING)
    @Column(name = "vendor_type", nullable = false)
    private VendorType vendorType;

    @Size(max = 500, message = "Notes must be less than 500 characters")
    @Column(name = "notes")
    private String notes;

    @Size(max = 500, message = "Contact Details must be less than 500 characters")
    @Column(nullable = false,name = "contact_details")
    private String contactDetails;

    @Column(nullable = false)
    @Pattern(
            regexp = "^[0-9]+-[0-9]+$",
            message = "Price range must be in the format 'min-max' with non-negative integers"
    )
    private String priceRange;

    @NotNull(message = "Vendor availability status is mandatory")
    @Column(name = "available", nullable = false)
    private Boolean available = false;

}
