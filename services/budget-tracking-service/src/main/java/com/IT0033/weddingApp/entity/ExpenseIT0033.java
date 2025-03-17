package com.IT0033.weddingApp.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "expenses")
public class ExpenseIT0033 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id", updatable = false, nullable = false)
    private Long expenseId;

    @NotBlank(message = "Expense description is mandatory")
    @Size(max = 255, message = "Description must be less than 255 characters")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Expense amount is mandatory")
    @Positive(message = "Expense amount must be a positive number")
    @Column(name = "amount", nullable = false)
    private Double amount;

    @NotNull(message = "Expense date is mandatory")
    @Column(name = "expense_date", nullable = false)
    private LocalDateTime expenseDate;

    // Many-to-One relationship with Budget
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id", nullable = false)
    private BudgetIT0033 budget;

    // Vendor ID as a simple reference (no foreign key constraint)
    @Column(name = "vendor_id", nullable = true)
    private Long vendorId;
}
