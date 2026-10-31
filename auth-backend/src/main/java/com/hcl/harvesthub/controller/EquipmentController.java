
package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Equipment;
import com.hcl.harvesthub.service.EquipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipment")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://harvest-hub-e7oe-theta.vercel.app"
})
public class EquipmentController {

    @Autowired
    private EquipmentService service;

    @PostMapping("/add")
    public Equipment add(
            @RequestBody Equipment equipment,
            @RequestParam Long userId) {
        return service.save(equipment, userId);
    }

    @PostMapping("/addAll")
    public List<Equipment> addAll(
            @RequestBody List<Equipment> equipment,
            @RequestParam Long userId) {
        return service.saveAll(equipment, userId);
    }

    @GetMapping("/all")
    public List<Equipment> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public Equipment getById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @PutMapping("/update/{id}")
    public Equipment update(
            @PathVariable Long id,
            @RequestBody Equipment equipment,
            @RequestParam Long userId) {
        return service.update(id, equipment, userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.delete(id, userId);
    }

    @PostMapping("/post/{id}")
    public Equipment postById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        return service.deleteAll(userId);
    }

    @GetMapping("/search")
    public List<Equipment> search(
            @RequestParam String equipmentName,
            @RequestParam Long userId) {
        return service.search(equipmentName, userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.count(userId);
    }
}
