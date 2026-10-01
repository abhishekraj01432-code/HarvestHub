package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.CropPlan;
import com.hcl.harvesthub.repository.CropPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropPlanService {

    @Autowired
    private CropPlanRepository repository;

    public CropPlan save(CropPlan cropPlan) {
        return repository.save(cropPlan);
    }

    public List<CropPlan> saveAll(List<CropPlan> cropPlans) {
        return repository.saveAll(cropPlans);
    }

    public List<CropPlan> getAll() {
        return repository.findAll();
    }

    public CropPlan getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public CropPlan update(Long id, CropPlan cropPlan) {
        cropPlan.setId(id);
        return repository.save(cropPlan);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<CropPlan> search(String cropName) {
        return repository.findByCropNameContainingIgnoreCase(cropName);
    }
}