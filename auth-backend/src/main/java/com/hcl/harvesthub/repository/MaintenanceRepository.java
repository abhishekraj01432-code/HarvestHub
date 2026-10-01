package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findByEquipmentNameContainingIgnoreCase(String equipmentName);

}