
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Expense;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.ExpenseRepository;
import com.hcl.harvesthub.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + userId));
    }

    public Expense save(Expense expense, Long userId) {
        expense.setId(null);
        expense.setUser(findUser(userId));
        return repository.save(expense);
    }

    public List<Expense> saveAll(List<Expense> expenses, Long userId) {
        User user = findUser(userId);

        for (Expense expense : expenses) {
            expense.setId(null);
            expense.setUser(user);
        }

        return repository.saveAll(expenses);
    }

    public List<Expense> getAll(Long userId) {
        findUser(userId);
        return repository.findByUser_Id(userId);
    }

    public Expense getById(Long id, Long userId) {
        return repository.findById(id)
                .filter(expense -> expense.getUser() != null
                        && expense.getUser().getId().equals(userId))
                .orElseThrow(() ->
                        new RuntimeException("Expense not found for this user"));
    }

    public Expense update(Long id, Expense updatedExpense, Long userId) {
        Expense existing = getById(id, userId);

        existing.setExpenseType(updatedExpense.getExpenseType());
        existing.setAmount(updatedExpense.getAmount());
        existing.setExpenseDate(updatedExpense.getExpenseDate());
        existing.setDescription(updatedExpense.getDescription());

        return repository.save(existing);
    }

    public String delete(Long id, Long userId) {
        Expense expense = getById(id, userId);
        repository.delete(expense);
        return "Expense deleted successfully";
    }

    public String deleteAll(Long userId) {
        List<Expense> expenses = getAll(userId);
        repository.deleteAll(expenses);
        return "All expenses for this user deleted successfully";
    }

    public List<Expense> search(String expenseType, Long userId) {
        findUser(userId);
        return repository.findByUser_IdAndExpenseTypeContainingIgnoreCase(
                userId, expenseType
        );
    }

    public Double getTotalAmount(Long userId) {
        List<Expense> expenses = getAll(userId);
        double total = 0.0;

        for (Expense expense : expenses) {
            if (expense.getAmount() != null) {
                total += expense.getAmount();
            }
        }

        return total;
    }
}
