package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Field;
import com.hcl.harvesthub.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
@CrossOrigin(origins = "http://localhost:4200")
public class FieldController {

    @Autowired
    private FieldService service;

    @PostMapping("/add")
    public Field add(@RequestBody Field field) {
        return service.save(field);
    }

    @PostMapping("/addAll")
    public List<Field> addAll(@RequestBody List<Field> fields) {
        return service.saveAll(fields);
    }

    @GetMapping("/all")
    public List<Field> all() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public Field getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/update/{id}")
    public Field update(@PathVariable Long id,
                        @RequestBody Field field) {
        return service.update(id, field);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Field deleted successfully";
    }

    @PostMapping("/post/{id}")
    public Field postById(@PathVariable Long id) {
        return service.getById(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "All field records deleted successfully";
    }

    @GetMapping("/search")
    public List<Field> search(@RequestParam String fieldName) {
        return service.search(fieldName);
    }

    @GetMapping("/count")
    public long count() {
        return service.getAll().size();
    }
}