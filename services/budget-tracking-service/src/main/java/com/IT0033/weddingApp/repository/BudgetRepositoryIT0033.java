package com.IT0033.weddingApp.repository;

import com.IT0033.weddingApp.entity.BudgetIT0033;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepositoryIT0033 extends JpaRepository<BudgetIT0033,Long> {
    boolean existsByBudgetName(String budgetName);
}
