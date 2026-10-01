package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.CropPlan;
import com.hcl.harvesthub.service.CropPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crop-plan")
@CrossOrigin(origins = "http://localhost:4200")
public class CropPlanController {

    @Autowired
    private CropPlanService service;

    @PostMapping("/add")
    public CropPlan add(@RequestBody CropPlan cropPlan) {
        return service.save(cropPlan);
    }

    @PostMapping("/addAll")
    public List<CropPlan> addAll(@RequestBody List<CropPlan> cropPlans) {
        return service.saveAll(cropPlans);
    }

    @GetMapping("/all")
    public List<CropPlan> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public CropPlan getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public CropPlan update(
            @PathVariable Long id,
            @RequestBody CropPlan cropPlan) {

        return service.update(id, cropPlan);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Crop plan deleted successfully";
    }

    @PostMapping("/post/{id}")
    public CropPlan postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "All crop plans deleted successfully";
    }

    @GetMapping("/search")
    public List<CropPlan> search(@RequestParam String cropName) {
        return service.search(cropName);
    }

    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}