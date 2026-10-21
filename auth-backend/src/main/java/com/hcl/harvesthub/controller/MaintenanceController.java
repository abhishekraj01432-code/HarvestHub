
package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Maintenance;
import com.hcl.harvesthub.service.MaintenanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/maintenance")
@CrossOrigin(origins = "http://localhost:4200")
public class MaintenanceController {

    @Autowired
    private MaintenanceService service;

    @PostMapping("/add")
    public Maintenance add(
            @RequestBody Maintenance maintenance,
            @RequestParam Long userId) {
        return service.save(maintenance, userId);
    }

    @PostMapping("/addAll")
    public List<Maintenance> addAll(
            @RequestBody List<Maintenance> maintenances,
            @RequestParam Long userId) {
        return service.saveAll(maintenances, userId);
    }

    @GetMapping("/all")
    public List<Maintenance> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Maintenance> getById(
            @PathVariable Long id,
            @RequestParam Long userId) {

        Maintenance maintenance = service.getById(id, userId);

        if (maintenance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(maintenance);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Maintenance> update(
            @PathVariable Long id,
            @RequestBody Maintenance maintenance,
            @RequestParam Long userId) {

        Maintenance updated = service.update(id, maintenance, userId);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id,
            @RequestParam Long userId) {

        if (!service.delete(id, userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Maintenance record not found");
        }

        return ResponseEntity.ok("Maintenance deleted successfully");
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<Maintenance> postById(
            @PathVariable Long id,
            @RequestParam Long userId) {

        Maintenance maintenance = service.getById(id, userId);

        if (maintenance == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(maintenance);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        service.deleteAll(userId);
        return "Your maintenance records deleted successfully";
    }

    @GetMapping("/search")
    public List<Maintenance> search(
            @RequestParam String equipmentName,
            @RequestParam Long userId) {
        return service.search(equipmentName, userId);
    }

    @GetMapping("/total")
    public Double total(@RequestParam Long userId) {
        return service.getTotalCost(userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.count(userId);
    }
}
