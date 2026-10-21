
package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUser_Id(Long userId);

    List<Expense> findByUser_IdAndExpenseTypeContainingIgnoreCase(
            Long userId, String expenseType
    );
}
