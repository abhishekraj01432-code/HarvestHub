
package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Expense;
import com.hcl.harvesthub.service.ExpenseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@CrossOrigin(origins = "http://localhost:4200")
public class ExpenseController {

    @Autowired
    private ExpenseService service;

    @PostMapping("/add")
    public Expense add(
            @RequestBody Expense expense,
            @RequestParam Long userId) {
        return service.save(expense, userId);
    }

    @PostMapping("/addAll")
    public List<Expense> addAll(
            @RequestBody List<Expense> expenses,
            @RequestParam Long userId) {
        return service.saveAll(expenses, userId);
    }

    @GetMapping("/all")
    public List<Expense> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public Expense getById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @PutMapping("/update/{id}")
    public Expense update(
            @PathVariable Long id,
            @RequestBody Expense expense,
            @RequestParam Long userId) {
        return service.update(id, expense, userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.delete(id, userId);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        return service.deleteAll(userId);
    }

    @PostMapping("/post/{id}")
    public Expense postById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @GetMapping("/search")
    public List<Expense> search(
            @RequestParam String expenseType,
            @RequestParam Long userId) {
        return service.search(expenseType, userId);
    }

    @GetMapping("/total")
    public Double total(@RequestParam Long userId) {
        return service.getTotalAmount(userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.getAll(userId).size();
    }
}
