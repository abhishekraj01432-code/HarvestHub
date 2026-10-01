package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Harvest;
import com.hcl.harvesthub.repository.HarvestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HarvestService {

    @Autowired
    private HarvestRepository repository;

    public Harvest save(Harvest harvest) {
        return repository.save(harvest);
    }

    public List<Harvest> saveAll(List<Harvest> harvests) {
        return repository.saveAll(harvests);
    }

    public List<Harvest> getAll() {
        return repository.findAll();
    }

    public Harvest getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Harvest update(Long id, Harvest harvest) {
        harvest.setId(id);
        return repository.save(harvest);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Harvest> search(String quality) {
        return repository.findByQualityContainingIgnoreCase(quality);
    }

    public List<Harvest> searchByCrop(String cropName) {
        return repository.findByCropNameContainingIgnoreCase(cropName);
    }

    public Double getTotalQuantity() {

        Double total = 0.0;

        for (Harvest harvest : repository.findAll()) {

            if (harvest.getQuantity() != null) {
                total += harvest.getQuantity();
            }
        }

        return total;
    }
}