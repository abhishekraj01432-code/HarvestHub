
package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.RotationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RotationRecordRepository
        extends JpaRepository<RotationRecord, Long> {

    List<RotationRecord> findByUser_Id(Long userId);

    Optional<RotationRecord> findByIdAndUser_Id(Long id, Long userId);

    List<RotationRecord> findByUser_IdAndFieldNameContainingIgnoreCase(
            Long userId, String fieldName);
}
