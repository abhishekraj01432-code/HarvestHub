
package com.hcl.harvesthub.controller;

import com.hcl.harvesthub.model.Field;
import com.hcl.harvesthub.service.FieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/field")
@CrossOrigin(origins = {
        "http://localhost:4200",
        "https://harvest-hub-e7oe-theta.vercel.app"
})
public class FieldController {

    @Autowired
    private FieldService service;

    @PostMapping("/add")
    public Field add(@RequestBody Field field,
                     @RequestParam Long userId) {
        return service.save(field, userId);
    }

    @PostMapping("/addAll")
    public List<Field> addAll(@RequestBody List<Field> fields,
                              @RequestParam Long userId) {
        return service.saveAll(fields, userId);
    }

    @GetMapping("/all")
    public List<Field> all(@RequestParam Long userId) {
        return service.getAll(userId);
    }

    @GetMapping("/id/{id}")
    public Field getById(@PathVariable Long id,
                         @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @PutMapping("/update/{id}")
    public Field update(@PathVariable Long id,
                        @RequestBody Field field,
                        @RequestParam Long userId) {
        return service.update(id, field, userId);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         @RequestParam Long userId) {
        return service.delete(id, userId);
    }

    @PostMapping("/post/{id}")
    public Field postById(@PathVariable Long id,
                          @RequestParam Long userId) {
        return service.getById(id, userId);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAll(@RequestParam Long userId) {
        service.deleteAll(userId);
        return "All field records deleted successfully";
    }

    @GetMapping("/search")
    public List<Field> search(@RequestParam String fieldName,
                              @RequestParam Long userId) {
        return service.search(fieldName, userId);
    }

    @GetMapping("/count")
    public long count(@RequestParam Long userId) {
        return service.getAll(userId).size();
    }
}
