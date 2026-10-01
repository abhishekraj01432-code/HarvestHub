package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.FarmProfile;
import com.hcl.harvesthub.service.FarmProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farm-profile")
@CrossOrigin(origins = "http://localhost:4200")
public class FarmProfileController {

    @Autowired
    private FarmProfileService service;

    @PostMapping("/add")
    public FarmProfile add(@RequestBody FarmProfile farmProfile) {
        return service.save(farmProfile);
    }

    @PostMapping("/addAll")
    public List<FarmProfile> addAll(@RequestBody List<FarmProfile> farmProfiles) {
        return service.saveAll(farmProfiles);
    }

    @GetMapping("/all")
    public List<FarmProfile> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public FarmProfile getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public FarmProfile update(
            @PathVariable Long id,
            @RequestBody FarmProfile farmProfile) {

        return service.update(id, farmProfile);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Farm profile deleted successfully";
    }

    @PostMapping("/post/{id}")
    public FarmProfile postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "All farm profiles deleted successfully";
    }

    @GetMapping("/search")
    public List<FarmProfile> search(@RequestParam String farmName) {
        return service.search(farmName);
    }

    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}