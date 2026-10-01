package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Crop;
import com.hcl.harvesthub.repository.CropRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {

    @Autowired
    private CropRepository repository;

    // Insert
    public Crop save(Crop crop) {
        return repository.save(crop);
    }

    // Read All
    public List<Crop> getAll() {
        return repository.findAll();
    }

    // Get By ID
    public Crop getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Update
    public Crop update(Long id, Crop crop) {
        crop.setId(id);
        return repository.save(crop);
    }

    // Delete
    public void delete(Long id) {
        repository.deleteById(id);
    }
    // Delete All
    public void deleteAll() {
        repository.deleteAll();
    }

    // Search
    public List<Crop> search(String cropName) {
        return repository.findByCropNameContainingIgnoreCase(cropName);
    }
}