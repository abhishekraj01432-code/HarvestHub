
package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Maintenance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findByUser_Id(Long userId);

    Optional<Maintenance> findByIdAndUser_Id(Long id, Long userId);

    List<Maintenance> findByUser_IdAndEquipmentNameContainingIgnoreCase(
            Long userId, String equipmentName);
}
