package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Maintenance;
import com.hcl.harvesthub.repository.MaintenanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository repository;

    public Maintenance save(Maintenance maintenance) {
        return repository.save(maintenance);
    }

    public List<Maintenance> saveAll(List<Maintenance> maintenances) {
        return repository.saveAll(maintenances);
    }

    public List<Maintenance> getAll() {
        return repository.findAll();
    }

    public Maintenance getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Maintenance update(Long id, Maintenance maintenance) {
        maintenance.setId(id);
        return repository.save(maintenance);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Maintenance> search(String equipmentName) {
        return repository.findByEquipmentNameContainingIgnoreCase(equipmentName);
    }

    public Double getTotalCost() {

        Double total = 0.0;

        for (Maintenance maintenance : repository.findAll()) {

            if (maintenance.getCost() != null) {
                total += maintenance.getCost();
            }

        }

        return total;
    }
}