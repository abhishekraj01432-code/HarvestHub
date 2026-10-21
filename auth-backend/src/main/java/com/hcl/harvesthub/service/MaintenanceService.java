
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Maintenance;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.MaintenanceRepository;
import com.hcl.harvesthub.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaintenanceService {

    @Autowired
    private MaintenanceRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Maintenance save(Maintenance maintenance, Long userId) {
        maintenance.setId(null);
        maintenance.setUser(findUser(userId));
        return repository.save(maintenance);
    }

    public List<Maintenance> saveAll(List<Maintenance> maintenances, Long userId) {
        User user = findUser(userId);

        for (Maintenance maintenance : maintenances) {
            maintenance.setId(null);
            maintenance.setUser(user);
        }

        return repository.saveAll(maintenances);
    }

    public List<Maintenance> getAll(Long userId) {
        findUser(userId);
        return repository.findByUser_Id(userId);
    }

    public Maintenance getById(Long id, Long userId) {
        return repository.findByIdAndUser_Id(id, userId).orElse(null);
    }

    public Maintenance update(Long id, Maintenance details, Long userId) {
        Maintenance existing = repository.findByIdAndUser_Id(id, userId)
                .orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setEquipmentName(details.getEquipmentName());
        existing.setMaintenanceDate(details.getMaintenanceDate());
        existing.setNextMaintenanceDate(details.getNextMaintenanceDate());
        existing.setMaintenanceType(details.getMaintenanceType());
        existing.setCost(details.getCost());
        existing.setStatus(details.getStatus());

        return repository.save(existing);
    }

    public boolean delete(Long id, Long userId) {
        Maintenance maintenance = repository.findByIdAndUser_Id(id, userId)
                .orElse(null);

        if (maintenance == null) {
            return false;
        }

        repository.delete(maintenance);
        return true;
    }

    public void deleteAll(Long userId) {
        repository.deleteAll(repository.findByUser_Id(userId));
    }

    public List<Maintenance> search(String equipmentName, Long userId) {
        findUser(userId);
        return repository.findByUser_IdAndEquipmentNameContainingIgnoreCase(
                userId, equipmentName);
    }

    public Double getTotalCost(Long userId) {
        return getAll(userId).stream()
                .map(Maintenance::getCost)
                .filter(cost -> cost != null)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    public long count(Long userId) {
        return getAll(userId).size();
    }
}
