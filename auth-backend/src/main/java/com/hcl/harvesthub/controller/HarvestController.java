
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
    public Harvest add(
            @RequestBody Harvest harvest,
            @RequestParam Long userId) {
        return service.save(harvest, userId);
    }

    @PostMapping("/addAll")
    public List<Harvest> addAll(
            @RequestBody List<Harvest> harvests,
            @RequestParam Long userId) {
        return service.saveAll(harvests, userId);
    }

    @GetMapping("/all")
    public List<Harvest> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public Harvest getById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @PutMapping("/update/{id}")
    public Harvest update(
            @PathVariable Long id,
            @RequestBody Harvest harvest,
            @RequestParam Long userId) {
        return service.update(id, harvest, userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.delete(id, userId);
    }

    @PostMapping("/post/{id}")
    public Harvest postById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        return service.deleteAll(userId);
    }

    @GetMapping("/search")
    public List<Harvest> search(
            @RequestParam String quality,
            @RequestParam Long userId) {
        return service.search(quality, userId);
    }

    @GetMapping("/search/crop")
    public List<Harvest> searchByCrop(
            @RequestParam String cropName,
            @RequestParam Long userId) {
        return service.searchByCrop(cropName, userId);
    }

    @GetMapping("/total")
    public Double total(@RequestParam Long userId) {
        return service.getTotalQuantity(userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.getAll(userId).size();
    }
}
