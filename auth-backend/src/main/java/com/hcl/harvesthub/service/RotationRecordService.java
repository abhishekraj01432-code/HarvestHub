package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.RotationRecord;
import com.hcl.harvesthub.repository.RotationRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotationRecordService {

    @Autowired
    private RotationRecordRepository repository;

    public RotationRecord save(RotationRecord rotationRecord) {
        return repository.save(rotationRecord);
    }

    public List<RotationRecord> saveAll(List<RotationRecord> rotationRecords) {
        return repository.saveAll(rotationRecords);
    }

    public List<RotationRecord> getAll() {
        return repository.findAll();
    }

    public RotationRecord getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public RotationRecord update(Long id, RotationRecord rotationRecord) {
        rotationRecord.setId(id);
        return repository.save(rotationRecord);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public List<RotationRecord> search(String fieldName) {
        return repository.findByFieldNameContainingIgnoreCase(fieldName);
    }
}