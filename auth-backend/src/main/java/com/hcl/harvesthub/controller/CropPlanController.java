
package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.CropPlan;
import com.hcl.harvesthub.service.CropPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/crop-plan")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://harvest-hub-e7oe-theta.vercel.app"
})
public class CropPlanController {

    @Autowired
    private CropPlanService service;

    @PostMapping("/add")
    public CropPlan add(
            @RequestBody CropPlan cropPlan,
            @RequestParam Long userId) {
        return service.save(cropPlan, userId);
    }

    @PostMapping("/addAll")
    public List<CropPlan> addAll(
            @RequestBody List<CropPlan> cropPlans,
            @RequestParam Long userId) {
        return service.saveAll(cropPlans, userId);
    }

    @GetMapping("/all")
    public List<CropPlan> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public CropPlan getById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @PutMapping("/update/{id}")
    public CropPlan update(
            @PathVariable Long id,
            @RequestBody CropPlan cropPlan,
            @RequestParam Long userId) {
        return service.update(id, cropPlan, userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.delete(id, userId);
    }

    @PostMapping("/post/{id}")
    public CropPlan postById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        service.deleteAll(userId);
        return "All crop plans deleted successfully";
    }

    @GetMapping("/search")
    public List<CropPlan> search(
            @RequestParam String cropName,
            @RequestParam Long userId) {
        return service.search(cropName, userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.getAll(userId).size();
    }
}
