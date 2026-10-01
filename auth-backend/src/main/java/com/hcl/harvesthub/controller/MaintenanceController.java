package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Maintenance;
import com.hcl.harvesthub.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
@CrossOrigin(origins = "http://localhost:4200")
public class MaintenanceController {

    @Autowired
    private MaintenanceService service;

    @PostMapping("/add")
    public Maintenance add(@RequestBody Maintenance maintenance) {
        return service.save(maintenance);
    }

    @PostMapping("/addAll")
    public List<Maintenance> addAll(
            @RequestBody List<Maintenance> maintenances) {

        return service.saveAll(maintenances);
    }

    @GetMapping("/all")
    public List<Maintenance> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public Maintenance getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Maintenance update(
            @PathVariable Long id,
            @RequestBody Maintenance maintenance) {

        return service.update(id, maintenance);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "Maintenance deleted successfully";
    }

    @PostMapping("/post/{id}")
    public Maintenance postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {

        service.deleteAll();

        return "All maintenance records deleted successfully";
    }

    @GetMapping("/search")
    public List<Maintenance> search(
            @RequestParam String equipmentName) {

        return service.search(equipmentName);
    }

    @GetMapping("/total")
    public Double total() {
        return service.getTotalCost();
    }

    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}