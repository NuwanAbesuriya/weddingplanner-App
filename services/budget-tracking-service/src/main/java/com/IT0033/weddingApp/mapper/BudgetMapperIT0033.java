package com.IT0033.weddingApp.mapper;

import com.IT0033.weddingApp.dto.BudgetDTOIT0033;
import com.IT0033.weddingApp.dto.ExpenseDTOIT0033;
import com.IT0033.weddingApp.entity.ExpenseIT0033;
import com.IT0033.weddingApp.entity.BudgetIT0033;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BudgetMapperIT0033 {


    // Map Budget Entity to DTO
    public BudgetDTOIT0033 mapToBudgetDto(BudgetIT0033 budget) {
        Set<ExpenseDTOIT0033> expenseDTOs = budget.getExpenses().stream()
                .map(this::mapToExpenseDto)
                .collect(Collectors.toSet());

        BudgetDTOIT0033 budgetDTO = new BudgetDTOIT0033();
        budgetDTO.setBudgetId(budget.getBudgetId());
        budgetDTO.setBudgetName(budget.getBudgetName());
        budgetDTO.setTotalBudget(budget.getTotalBudget());
        budgetDTO.setExpenses(expenseDTOs);
        // Optionally, you can calculate and set remainingBudget here if needed.

        return budgetDTO;
    }

    // Map Budget DTO to Entity
    public BudgetIT0033 mapToBudgetEntity(BudgetDTOIT0033 budgetDTO) {
        BudgetIT0033 budget = new BudgetIT0033();
        budget.setBudgetId(budgetDTO.getBudgetId());
        budget.setBudgetName(budgetDTO.getBudgetName());
        budget.setTotalBudget(budgetDTO.getTotalBudget());
        // Expenses are managed separately in the service layer.
        return budget;
    }

    // Map Expense Entity to DTO
    public ExpenseDTOIT0033 mapToExpenseDto(ExpenseIT0033 expense) {
        ExpenseDTOIT0033 expenseDTO = new ExpenseDTOIT0033();
        expenseDTO.setExpenseId(expense.getExpenseId());
        expenseDTO.setDescription(expense.getDescription());
        expenseDTO.setAmount(expense.getAmount());
        expenseDTO.setExpenseDate(expense.getExpenseDate());
        expenseDTO.setBudgetId(expense.getBudget() != null ? expense.getBudget().getBudgetId() : null);
        expenseDTO.setVendorId(expense.getVendorId()); // Vendor ID is now a simple Long field
        expenseDTO.setEventId(null); // Set this if linking to an event is required.
        return expenseDTO;
    }

    // Map Expense DTO to Entity
    public ExpenseIT0033 mapToExpenseEntity(ExpenseDTOIT0033 expenseDTO, BudgetIT0033 budget) {
        ExpenseIT0033 expense = new ExpenseIT0033();
        expense.setExpenseId(expenseDTO.getExpenseId());
        expense.setDescription(expenseDTO.getDescription());
        expense.setAmount(expenseDTO.getAmount());
        expense.setExpenseDate(expenseDTO.getExpenseDate());
        expense.setBudget(budget);
        expense.setVendorId(expenseDTO.getVendorId()); // Set vendorId directly as a simple field
        return expense;
    }


}
