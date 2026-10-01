package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

    List<Equipment> findByEquipmentNameContainingIgnoreCase(String equipmentName);

}