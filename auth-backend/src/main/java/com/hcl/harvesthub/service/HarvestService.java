
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Harvest;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.HarvestRepository;
import com.hcl.harvesthub.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarvestService {

    @Autowired
    private HarvestRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + userId));
    }

    public Harvest save(Harvest harvest, Long userId) {
        harvest.setId(null);
        harvest.setUser(findUser(userId));
        return repository.save(harvest);
    }

    public List<Harvest> saveAll(List<Harvest> harvests, Long userId) {
        User user = findUser(userId);

        for (Harvest harvest : harvests) {
            harvest.setId(null);
            harvest.setUser(user);
        }

        return repository.saveAll(harvests);
    }

    public List<Harvest> getAll(Long userId) {
        findUser(userId);
        return repository.findByUser_Id(userId);
    }

    public Harvest getById(Long id, Long userId) {
        return repository.findById(id)
                .filter(harvest -> harvest.getUser() != null
                        && harvest.getUser().getId().equals(userId))
                .orElseThrow(() ->
                        new RuntimeException("Harvest not found for this user"));
    }

    public Harvest update(Long id, Harvest updated, Long userId) {
        Harvest existing = getById(id, userId);

        existing.setCropName(updated.getCropName());
        existing.setHarvestDate(updated.getHarvestDate());
        existing.setQuantity(updated.getQuantity());
        existing.setUnit(updated.getUnit());
        existing.setSellingPrice(updated.getSellingPrice());
        existing.setQuality(updated.getQuality());

        return repository.save(existing);
    }

    public String delete(Long id, Long userId) {
        Harvest harvest = getById(id, userId);
        repository.delete(harvest);
        return "Harvest deleted successfully";
    }

    public String deleteAll(Long userId) {
        List<Harvest> harvests = getAll(userId);
        repository.deleteAll(harvests);
        return "All harvest records for this user deleted successfully";
    }

    public List<Harvest> search(String quality, Long userId) {
        findUser(userId);
        return repository.findByUser_IdAndQualityContainingIgnoreCase(
                userId, quality);
    }

    public List<Harvest> searchByCrop(String cropName, Long userId) {
        findUser(userId);
        return repository.findByUser_IdAndCropNameContainingIgnoreCase(
                userId, cropName);
    }

    public Double getTotalQuantity(Long userId) {
        double total = 0.0;

        for (Harvest harvest : getAll(userId)) {
            if (harvest.getQuantity() != null) {
                total += harvest.getQuantity();
            }
        }

        return total;
    }
}
