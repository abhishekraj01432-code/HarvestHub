
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
    public FarmProfile add(
            @RequestBody FarmProfile farmProfile,
            @RequestParam Long userId) {
        return service.save(farmProfile, userId);
    }

    @PostMapping("/addAll")
    public List<FarmProfile> addAll(
            @RequestBody List<FarmProfile> farmProfiles,
            @RequestParam Long userId) {
        return service.saveAll(farmProfiles, userId);
    }

    @GetMapping("/all")
    public List<FarmProfile> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public FarmProfile getById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @PutMapping("/update/{id}")
    public FarmProfile update(
            @PathVariable Long id,
            @RequestBody FarmProfile farmProfile,
            @RequestParam Long userId) {
        return service.update(id, farmProfile, userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.delete(id, userId);
    }

    @PostMapping("/post/{id}")
    public FarmProfile postById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        service.deleteAll(userId);
        return "All farm profiles deleted successfully";
    }

    @GetMapping("/search")
    public List<FarmProfile> search(
            @RequestParam String farmName,
            @RequestParam Long userId) {
        return service.search(farmName, userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.getAll(userId).size();
    }
}
