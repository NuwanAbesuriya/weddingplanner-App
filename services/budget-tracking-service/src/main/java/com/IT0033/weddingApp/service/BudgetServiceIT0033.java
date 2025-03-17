package com.IT0033.weddingApp.service;

import com.IT0033.weddingApp.client.VendorClientIT0033;
import com.IT0033.weddingApp.dto.BudgetDTOIT0033;
import com.IT0033.weddingApp.dto.ExpenseDTOIT0033;
import com.IT0033.weddingApp.dto.VendorDTOIT0033;
import com.IT0033.weddingApp.entity.ExpenseIT0033;
import com.IT0033.weddingApp.entity.VendorIT0033;
import com.IT0033.weddingApp.exception.ResourceNotFoundException;
import com.IT0033.weddingApp.repository.BudgetRepositoryIT0033;
import com.IT0033.weddingApp.repository.ExpenseRepositoryIT0033;
import com.IT0033.weddingApp.entity.BudgetIT0033;
import com.IT0033.weddingApp.mapper.BudgetMapperIT0033;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BudgetServiceIT0033 {

    private final BudgetRepositoryIT0033 budgetRepository;
    private final ExpenseRepositoryIT0033 expenseRepository;
    private final VendorClientIT0033 vendorClient; // Injected directly
    private final BudgetMapperIT0033 budgetMapper;

    public BudgetDTOIT0033 setBudget(BudgetDTOIT0033 budgetDTO) {
        log.info("Setting a new budget with name: {}", budgetDTO.getBudgetName());

        if (budgetRepository.existsByBudgetName(budgetDTO.getBudgetName())) {
            throw new IllegalArgumentException("Budget with name '" + budgetDTO.getBudgetName() + "' already exists.");
        }

        BudgetIT0033 budgetEntity = budgetMapper.mapToBudgetEntity(budgetDTO);
        BudgetIT0033 savedBudget = budgetRepository.save(budgetEntity);

        return budgetMapper.mapToBudgetDto(savedBudget);
    }
    @Transactional
    public ExpenseDTOIT0033 addExpense(Long budgetId, ExpenseDTOIT0033 expenseDTO) {
        log.info("Adding expense '{}' to budget ID: {}", expenseDTO.getDescription(), budgetId);

        // Validate Budget
        BudgetIT0033 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        // Validate Vendor
        if (expenseDTO.getVendorId() != null) {
            try {
                vendorClient.checkVendorAvailability(expenseDTO.getVendorId());
            } catch (FeignException.NotFound e) {
                throw new ResourceNotFoundException("Vendor not found with ID: " + expenseDTO.getVendorId());
            } catch (FeignException e) {
                throw new IllegalStateException("Vendor service is unavailable.");
            }
        }

        // Map DTO to Entity
        ExpenseIT0033 expenseEntity = budgetMapper.mapToExpenseEntity(expenseDTO, budget);

        // Set Vendor ID
        expenseEntity.setVendorId(expenseDTO.getVendorId());

        // Save Expense
        ExpenseIT0033 savedExpense = expenseRepository.save(expenseEntity);

        return budgetMapper.mapToExpenseDto(savedExpense);
    }



    private VendorIT0033 mapVendorDtoToEntity(VendorDTOIT0033 vendorDTO) {
        VendorIT0033 vendor = new VendorIT0033();
        vendor.setId(vendorDTO.getId());
        vendor.setName(vendorDTO.getName());
        vendor.setVendorType(vendorDTO.getVendorType());
        vendor.setNotes(vendorDTO.getNotes());
        vendor.setContactDetails(vendorDTO.getContactDetails());
        vendor.setPriceRange(vendorDTO.getPriceRange());
        vendor.setAvailable(vendorDTO.getAvailable());
        return vendor;
    }


    @Transactional(readOnly = true)
    public BudgetDTOIT0033 getBudgetSummary(Long budgetId) {
        // Fetch the budget from the repository
        BudgetIT0033 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        // Calculate total expenses
        double totalExpenses = budget.getExpenses().stream()
                .mapToDouble(ExpenseIT0033::getAmount)
                .sum();

        // Calculate the remaining budget
        double remainingBudget = budget.getTotalBudget() - totalExpenses;

        // Map the Budget entity to DTO
        BudgetDTOIT0033 budgetDTO = budgetMapper.mapToBudgetDto(budget);
        budgetDTO.setRemainingBudget(remainingBudget);  // Set the calculated remaining budget

        return budgetDTO;
    }


    @Transactional(readOnly = true)
    public ExpenseDTOIT0033 getExpenseDetails(Long expenseId) {
        log.info("Fetching details for expense ID: {}", expenseId);

        ExpenseIT0033 expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new ResourceNotFoundException("Expense not found with ID: " + expenseId));

        return budgetMapper.mapToExpenseDto(expense);
    }



    @Transactional(readOnly = true)
    public List<ExpenseDTOIT0033> getAllExpenses(Long budgetId) {
        log.info("Fetching all expenses for budget ID: {}", budgetId);

        BudgetIT0033 budget = budgetRepository.findById(budgetId)
                .orElseThrow(() -> new ResourceNotFoundException("Budget not found with ID: " + budgetId));

        return budget.getExpenses().stream()
                .map(budgetMapper::mapToExpenseDto)
                .collect(Collectors.toList());
    }
}
