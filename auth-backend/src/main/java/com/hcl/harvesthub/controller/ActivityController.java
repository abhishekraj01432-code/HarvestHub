
package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Activity;
import com.hcl.harvesthub.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/activity")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://harvest-hub-e7oe-theta.vercel.app"
})
public class ActivityController {

    @Autowired
    private ActivityService service;

    @PostMapping("/add")
    public Activity add(
            @RequestBody Activity activity,
            @RequestParam Long userId) {
        return service.save(activity, userId);
    }

    @PostMapping("/addAll")
    public List<Activity> addAll(
            @RequestBody List<Activity> activities,
            @RequestParam Long userId) {
        return service.saveAll(activities, userId);
    }

    @GetMapping("/all")
    public List<Activity> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public Activity getById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @PutMapping("/update/{id}")
    public Activity update(
            @PathVariable Long id,
            @RequestBody Activity activity,
            @RequestParam Long userId) {
        return service.update(id, activity, userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.delete(id, userId);
    }

    @PostMapping("/post/{id}")
    public Activity postById(
            @PathVariable Long id,
            @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        service.deleteAll(userId);
        return "All activities deleted successfully";
    }

    @GetMapping("/search")
    public List<Activity> search(
            @RequestParam String activityName,
            @RequestParam Long userId) {
        return service.search(activityName, userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.getAll(userId).size();
    }
}
