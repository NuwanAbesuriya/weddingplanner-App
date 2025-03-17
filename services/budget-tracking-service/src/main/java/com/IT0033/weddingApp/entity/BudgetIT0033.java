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

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "budgets")
public class BudgetIT0033 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_id", updatable = false, nullable = false)
    private Long budgetId;

    @NotBlank(message = "Budget name is mandatory")
    @Size(max = 100, message = "Budget name must be less than 100 characters")
    @Column(name = "budget_name", nullable = false, unique = true)
    private String budgetName;

    @NotNull(message = "Total budget amount is mandatory")
    @Positive(message = "Total budget must be a positive number")
    @Column(name = "total_budget", nullable = false)
    private Double totalBudget;

    // One-to-Many relationship with Expense
    // Initialize the expenses set to prevent NullPointerException
    @OneToMany(mappedBy = "budget", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ExpenseIT0033> expenses = new HashSet<>();

    @Transient
    // New field for remaining budget
    private Double remainingBudget;
}
