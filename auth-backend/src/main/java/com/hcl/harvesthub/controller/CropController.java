package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Crop;
import com.hcl.harvesthub.service.CropService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/crop")
@CrossOrigin(origins = "http://localhost:4200")

public class CropController {

    @Autowired
    private CropService service;

    // Insert
    @PostMapping("/add")
    public Crop add(@RequestBody Crop crop) {
        return service.save(crop);
    }

    // Insert Multiple
    @PostMapping("/addAll")
    public List<Crop> addAll(@RequestBody List<Crop> crops) {
        for (Crop crop : crops) {
            service.save(crop);
        }
        return crops;
    }

    // Read All
    @GetMapping("/all")
    public List<Crop> all() {
        return service.getAll();
    }

    // Get By ID
    @GetMapping("/id/{id}")
    public Crop getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // Update
    @PutMapping("/update/{id}")
    public Crop update(@PathVariable Long id,
                       @RequestBody Crop crop) {
        return service.update(id, crop);
    }

    // Delete
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Crop deleted successfully";
    }

    // Post By ID
    @PostMapping("/post/{id}")
    public Crop postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "All crop records deleted successfully";
    }


    // Search
    @GetMapping("/search")
    public List<Crop> search(@RequestParam String cropName) {
        return service.search(cropName);
    }

    // Count
    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}