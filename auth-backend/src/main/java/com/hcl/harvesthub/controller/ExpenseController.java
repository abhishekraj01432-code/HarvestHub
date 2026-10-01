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
    public Expense add(@RequestBody Expense expense) {
        return service.save(expense);
    }

    @PostMapping("/addAll")
    public List<Expense> addAll(@RequestBody List<Expense> expenses) {
        return service.saveAll(expenses);
    }

    @GetMapping("/all")
    public List<Expense> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public Expense getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Expense update(
            @PathVariable Long id,
            @RequestBody Expense expense) {

        return service.update(id, expense);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Expense deleted successfully";
    }

    @PostMapping("/post/{id}")
    public Expense postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "All expense records deleted successfully";
    }

    @GetMapping("/search")
    public List<Expense> search(@RequestParam String expenseType) {
        return service.search(expenseType);
    }

    @GetMapping("/total")
    public Double total() {
        return service.getTotalAmount();
    }

    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}