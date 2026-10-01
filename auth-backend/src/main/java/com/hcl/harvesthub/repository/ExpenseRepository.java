package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByExpenseTypeContainingIgnoreCase(String expenseType);

}