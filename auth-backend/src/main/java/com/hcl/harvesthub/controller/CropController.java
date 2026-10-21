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
    public Crop add(@RequestBody Crop crop,
                    @RequestParam Long userId) {
        return service.save(crop, userId);
    }

    // Insert Multiple
    @PostMapping("/addAll")
    public List<Crop> addAll(@RequestBody List<Crop> crops,
                             @RequestParam Long userId) {

        for (Crop crop : crops) {
            service.save(crop, userId);
        }

        return service.getAll(userId);
    }

    // Read All - User Wise
    @GetMapping("/all")
    public List<Crop> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    // Get By ID - User Wise
    @GetMapping("/id/{id}")
    public Crop getById(@PathVariable Long id,
                        @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    // Update - User Wise
    @PutMapping("/update/{id}")
    public Crop update(@PathVariable Long id,
                       @RequestBody Crop crop,
                       @RequestParam Long userId) {
        return service.update(id, crop, userId);
    }

    // Delete - User Wise
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         @RequestParam Long userId) {
        return service.delete(id, userId);
    }

    // Post By ID
    @PostMapping("/post/{id}")
    public Crop postById(@PathVariable Long id,
                         @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    // Delete All - User Wise
    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {

        service.deleteAll(userId);

        return "All crop records deleted successfully";
    }

    // Search - User Wise
    @GetMapping("/search")
    public List<Crop> search(@RequestParam String cropName,
                             @RequestParam Long userId) {
        return service.search(cropName, userId);
    }

    // Count - User Wise
    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.getAll(userId).size();
    }
}