package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.RotationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RotationRecordRepository
        extends JpaRepository<RotationRecord, Long> {

    List<RotationRecord> findByFieldNameContainingIgnoreCase(String fieldName);

}