package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Equipment;
import com.hcl.harvesthub.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
@CrossOrigin(origins = "http://localhost:4200")
public class EquipmentController {

    @Autowired
    private EquipmentService service;

    @PostMapping("/add")
    public Equipment add(@RequestBody Equipment equipment) {
        return service.save(equipment);
    }

    @PostMapping("/addAll")
    public List<Equipment> addAll(@RequestBody List<Equipment> equipment) {
        return service.saveAll(equipment);
    }

    @GetMapping("/all")
    public List<Equipment> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public Equipment getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Equipment update(
            @PathVariable Long id,
            @RequestBody Equipment equipment) {

        return service.update(id, equipment);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Equipment deleted successfully";
    }

    @PostMapping("/post/{id}")
    public Equipment postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "All equipment records deleted successfully";
    }

    @GetMapping("/search")
    public List<Equipment> search(@RequestParam String equipmentName) {
        return service.search(equipmentName);
    }

    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}