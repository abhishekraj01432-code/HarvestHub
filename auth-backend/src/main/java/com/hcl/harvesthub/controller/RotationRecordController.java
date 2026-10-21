
package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.RotationRecord;
import com.hcl.harvesthub.service.RotationRecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rotation")
@CrossOrigin(origins = "http://localhost:4200")
public class RotationRecordController {

    @Autowired
    private RotationRecordService service;

    @PostMapping("/add")
    public RotationRecord add(
            @RequestBody RotationRecord record,
            @RequestParam Long userId) {
        return service.save(record, userId);
    }

    @PostMapping("/addAll")
    public List<RotationRecord> addAll(
            @RequestBody List<RotationRecord> records,
            @RequestParam Long userId) {
        return service.saveAll(records, userId);
    }

    @GetMapping("/all")
    public List<RotationRecord> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<RotationRecord> getById(
            @PathVariable Long id,
            @RequestParam Long userId) {

        RotationRecord record = service.getById(id, userId);

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(record);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RotationRecord> update(
            @PathVariable Long id,
            @RequestBody RotationRecord record,
            @RequestParam Long userId) {

        RotationRecord updated = service.update(id, record, userId);

        if (updated == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id,
            @RequestParam Long userId) {

        if (!service.delete(id, userId)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Rotation record not found");
        }

        return ResponseEntity.ok("Rotation record deleted successfully");
    }

    @PostMapping("/post/{id}")
    public ResponseEntity<RotationRecord> postById(
            @PathVariable Long id,
            @RequestParam Long userId) {

        RotationRecord record = service.getById(id, userId);

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(record);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        service.deleteAll(userId);
        return "Your rotation records deleted successfully";
    }

    @GetMapping("/search")
    public List<RotationRecord> search(
            @RequestParam String fieldName,
            @RequestParam Long userId) {
        return service.search(fieldName, userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.count(userId);
    }
}
