
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.CropPlan;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.CropPlanRepository;
import com.hcl.harvesthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CropPlanService {

    @Autowired
    private CropPlanRepository repository;

    @Autowired
    private UserRepository userRepository;

    public CropPlan save(CropPlan cropPlan, Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        cropPlan.setUser(user);
        return repository.save(cropPlan);
    }

    public List<CropPlan> saveAll(
            List<CropPlan> cropPlans, Long userId) {

        List<CropPlan> savedPlans = new ArrayList<>();

        for (CropPlan plan : cropPlans) {
            CropPlan saved = save(plan, userId);

            if (saved != null) {
                savedPlans.add(saved);
            }
        }

        return savedPlans;
    }

    public List<CropPlan> getAll(Long userId) {
        return repository.findByUser_Id(userId);
    }

    public CropPlan getById(Long id, Long userId) {
        CropPlan plan = repository.findById(id).orElse(null);

        if (plan != null &&
                plan.getUser() != null &&
                plan.getUser().getId().equals(userId)) {
            return plan;
        }

        return null;
    }

    public CropPlan update(
            Long id, CropPlan cropPlan, Long userId) {

        CropPlan existing = repository.findById(id).orElse(null);

        if (existing == null ||
                existing.getUser() == null ||
                !existing.getUser().getId().equals(userId)) {
            return null;
        }

        cropPlan.setId(id);
        cropPlan.setUser(existing.getUser());

        return repository.save(cropPlan);
    }

    public String delete(Long id, Long userId) {
        CropPlan plan = repository.findById(id).orElse(null);

        if (plan == null ||
                plan.getUser() == null ||
                !plan.getUser().getId().equals(userId)) {
            return "Crop plan not found";
        }

        repository.deleteById(id);
        return "Crop plan deleted successfully";
    }

    public void deleteAll(Long userId) {
        List<CropPlan> plans = repository.findByUser_Id(userId);
        repository.deleteAll(plans);
    }

    public List<CropPlan> search(String cropName, Long userId) {
        return repository
                .findByUser_IdAndCropNameContainingIgnoreCase(
                        userId, cropName
                );
    }
}
