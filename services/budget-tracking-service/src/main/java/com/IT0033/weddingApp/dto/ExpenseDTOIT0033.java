package com.IT0033.weddingApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExpenseDTOIT0033 {
    private Long expenseId;

    @NotBlank(message = "Expense description is mandatory")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @NotNull(message = "Expense amount is mandatory")
    @Positive(message = "Expense amount must be a positive number")
    private Double amount;

    @NotNull(message = "Expense date is mandatory")
    private LocalDateTime expenseDate;

    private Long budgetId; // For associating with Budget

    private Long vendorId; // To link expense to a vendor

    private Long eventId; // Optional: To link expense to an event
}
