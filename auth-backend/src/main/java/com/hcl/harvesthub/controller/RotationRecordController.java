package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.RotationRecord;
import com.hcl.harvesthub.service.RotationRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotation")
@CrossOrigin(origins = "http://localhost:4200")
public class RotationRecordController {

    @Autowired
    private RotationRecordService service;

    @PostMapping("/add")
    public RotationRecord add(@RequestBody RotationRecord rotationRecord) {
        return service.save(rotationRecord);
    }

    @PostMapping("/addAll")
    public List<RotationRecord> addAll(
            @RequestBody List<RotationRecord> rotationRecords) {

        return service.saveAll(rotationRecords);
    }

    @GetMapping("/all")
    public List<RotationRecord> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public RotationRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public RotationRecord update(
            @PathVariable Long id,
            @RequestBody RotationRecord rotationRecord) {

        return service.update(id, rotationRecord);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {

        service.delete(id);

        return "Rotation record deleted successfully";
    }

    @PostMapping("/post/{id}")
    public RotationRecord postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {

        service.deleteAll();

        return "All rotation records deleted successfully";
    }

    @GetMapping("/search")
    public List<RotationRecord> search(
            @RequestParam String fieldName) {

        return service.search(fieldName);
    }

    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}