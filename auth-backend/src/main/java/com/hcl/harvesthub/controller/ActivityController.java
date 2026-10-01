package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Activity;
import com.hcl.harvesthub.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = "http://localhost:4200")
public class ActivityController {

    @Autowired
    private ActivityService service;

    @PostMapping("/add")
    public Activity add(@RequestBody Activity activity) {
        return service.save(activity);
    }

    @PostMapping("/addAll")
    public List<Activity> addAll(@RequestBody List<Activity> activities) {
        return service.saveAll(activities);
    }

    @GetMapping("/all")
    public List<Activity> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public Activity getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Activity update(
            @PathVariable Long id,
            @RequestBody Activity activity) {

        return service.update(id, activity);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Activity deleted successfully";
    }

    @PostMapping("/post/{id}")
    public Activity postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "All activities deleted successfully";
    }

    @GetMapping("/search")
    public List<Activity> search(@RequestParam String activityName) {
        return service.search(activityName);
    }

    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}