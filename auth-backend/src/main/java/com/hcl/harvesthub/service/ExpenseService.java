package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Expense;
import com.hcl.harvesthub.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository repository;

    public Expense save(Expense expense) {
        return repository.save(expense);
    }

    public List<Expense> saveAll(List<Expense> expenses) {
        return repository.saveAll(expenses);
    }

    public List<Expense> getAll() {
        return repository.findAll();
    }

    public Expense getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Expense update(Long id, Expense expense) {
        expense.setId(id);
        return repository.save(expense);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Expense> search(String expenseType) {
        return repository.findByExpenseTypeContainingIgnoreCase(expenseType);
    }

    public Double getTotalAmount() {

        Double total = 0.0;

        for (Expense expense : repository.findAll()) {

            if (expense.getAmount() != null) {
                total += expense.getAmount();
            }

        }

        return total;
    }
}