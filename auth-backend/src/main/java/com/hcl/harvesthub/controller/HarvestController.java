package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Harvest;
import com.hcl.harvesthub.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/harvest")
@CrossOrigin(origins = "http://localhost:4200")
public class HarvestController {

    @Autowired
    private HarvestService service;

    @PostMapping("/add")
    public Harvest add(@RequestBody Harvest harvest) {
        return service.save(harvest);
    }

    @PostMapping("/addAll")
    public List<Harvest> addAll(@RequestBody List<Harvest> harvests) {
        return service.saveAll(harvests);
    }

    @GetMapping("/all")
    public List<Harvest> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public Harvest getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Harvest update(
            @PathVariable Long id,
            @RequestBody Harvest harvest) {

        return service.update(id, harvest);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Harvest deleted successfully";
    }

    @PostMapping("/post/{id}")
    public Harvest postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "All harvest records deleted successfully";
    }

    @GetMapping("/search")
    public List<Harvest> search(@RequestParam String quality) {
        return service.search(quality);
    }

    @GetMapping("/search/crop")
    public List<Harvest> searchByCrop(@RequestParam String cropName) {
        return service.searchByCrop(cropName);
    }

    @GetMapping("/total")
    public Double total() {
        return service.getTotalQuantity();
    }
}