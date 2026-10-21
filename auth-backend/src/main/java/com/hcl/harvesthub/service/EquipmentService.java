
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Equipment;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.EquipmentRepository;
import com.hcl.harvesthub.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found: " + userId));
    }

    public Equipment save(Equipment equipment, Long userId) {
        equipment.setId(null);
        equipment.setUser(findUser(userId));
        return repository.save(equipment);
    }

    public List<Equipment> saveAll(
            List<Equipment> equipmentList, Long userId) {

        User user = findUser(userId);

        for (Equipment equipment : equipmentList) {
            equipment.setId(null);
            equipment.setUser(user);
        }

        return repository.saveAll(equipmentList);
    }

    public List<Equipment> getAll(Long userId) {
        findUser(userId);
        return repository.findByUser_Id(userId);
    }

    public Equipment getById(Long id, Long userId) {
        return repository.findById(id)
                .filter(equipment -> equipment.getUser() != null
                        && equipment.getUser().getId().equals(userId))
                .orElseThrow(() ->
                        new RuntimeException(
                                "Equipment not found for this user"));
    }

    public Equipment update(
            Long id, Equipment updatedEquipment, Long userId) {

        Equipment existing = getById(id, userId);

        existing.setEquipmentName(updatedEquipment.getEquipmentName());
        existing.setEquipmentType(updatedEquipment.getEquipmentType());
        existing.setModel(updatedEquipment.getModel());
        existing.setPurchaseDate(updatedEquipment.getPurchaseDate());
        existing.setStatus(updatedEquipment.getStatus());

        return repository.save(existing);
    }

    public String delete(Long id, Long userId) {
        Equipment equipment = getById(id, userId);
        repository.delete(equipment);
        return "Equipment deleted successfully";
    }

    public String deleteAll(Long userId) {
        List<Equipment> equipmentList = getAll(userId);
        repository.deleteAll(equipmentList);
        return "All equipment records for this user deleted successfully";
    }

    public List<Equipment> search(
            String equipmentName, Long userId) {

        findUser(userId);

        return repository
                .findByUser_IdAndEquipmentNameContainingIgnoreCase(
                        userId, equipmentName);
    }

    public long count(Long userId) {
        return getAll(userId).size();
    }
}
