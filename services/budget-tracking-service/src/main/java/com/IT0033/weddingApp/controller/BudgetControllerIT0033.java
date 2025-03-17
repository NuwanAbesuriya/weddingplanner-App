package com.IT0033.weddingApp.controller;

import com.IT0033.weddingApp.dto.ExpenseDTOIT0033;
import com.IT0033.weddingApp.dto.BudgetDTOIT0033;

import com.IT0033.weddingApp.service.BudgetServiceIT0033;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetControllerIT0033 {

    private final BudgetServiceIT0033 budgetService;

    @PostMapping
    public ResponseEntity<BudgetDTOIT0033> setBudget(@Valid @RequestBody BudgetDTOIT0033 budgetDTO) {
        BudgetDTOIT0033 createdBudget = budgetService.setBudget(budgetDTO);
        return new ResponseEntity<>(createdBudget, HttpStatus.CREATED);
    }


    @PostMapping("/{budgetId}/expenses")
    public ResponseEntity<ExpenseDTOIT0033> addExpense(@PathVariable Long budgetId,
                                                       @Valid @RequestBody ExpenseDTOIT0033 expenseDTO) {
        ExpenseDTOIT0033 createdExpense = budgetService.addExpense(budgetId, expenseDTO);
        return new ResponseEntity<>(createdExpense, HttpStatus.CREATED);
    }


    @GetMapping("/{budgetId}/summary")
    public ResponseEntity<BudgetDTOIT0033> getBudgetSummary(@PathVariable Long budgetId) {
        BudgetDTOIT0033 budgetSummary = budgetService.getBudgetSummary(budgetId);
        return ResponseEntity.ok(budgetSummary);
    }


    @GetMapping("/expenses/{expenseId}")
    public ResponseEntity<ExpenseDTOIT0033> getExpenseDetails(@PathVariable Long expenseId) {
        ExpenseDTOIT0033 expenseDetails = budgetService.getExpenseDetails(expenseId);
        return ResponseEntity.ok(expenseDetails);
    }


    @GetMapping("/{budgetId}/expenses")
    public ResponseEntity<List<ExpenseDTOIT0033>> getAllExpenses(@PathVariable Long budgetId) {
        List<ExpenseDTOIT0033> expenses = budgetService.getAllExpenses(budgetId);
        return ResponseEntity.ok(expenses);
    }
}
