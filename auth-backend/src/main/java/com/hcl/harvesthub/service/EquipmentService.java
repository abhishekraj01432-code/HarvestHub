package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Equipment;
import com.hcl.harvesthub.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository repository;

    public Equipment save(Equipment equipment) {
        return repository.save(equipment);
    }

    public List<Equipment> saveAll(List<Equipment> equipment) {
        return repository.saveAll(equipment);
    }

    public List<Equipment> getAll() {
        return repository.findAll();
    }

    public Equipment getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Equipment update(Long id, Equipment equipment) {
        equipment.setId(id);
        return repository.save(equipment);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<Equipment> search(String equipmentName) {
        return repository.findByEquipmentNameContainingIgnoreCase(equipmentName);
    }
}