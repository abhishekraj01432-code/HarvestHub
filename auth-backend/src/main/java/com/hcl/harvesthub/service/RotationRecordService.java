
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.RotationRecord;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.RotationRecordRepository;
import com.hcl.harvesthub.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RotationRecordService {

    @Autowired
    private RotationRecordRepository repository;

    @Autowired
    private UserRepository userRepository;

    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public RotationRecord save(RotationRecord record, Long userId) {
        record.setId(null);
        record.setUser(findUser(userId));
        return repository.save(record);
    }

    public List<RotationRecord> saveAll(
            List<RotationRecord> records, Long userId) {

        User user = findUser(userId);

        for (RotationRecord record : records) {
            record.setId(null);
            record.setUser(user);
        }

        return repository.saveAll(records);
    }

    public List<RotationRecord> getAll(Long userId) {
        findUser(userId);
        return repository.findByUser_Id(userId);
    }

    public RotationRecord getById(Long id, Long userId) {
        return repository.findByIdAndUser_Id(id, userId).orElse(null);
    }

    public RotationRecord update(
            Long id, RotationRecord details, Long userId) {

        RotationRecord existing = repository
                .findByIdAndUser_Id(id, userId)
                .orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setFieldName(details.getFieldName());
        existing.setPreviousCrop(details.getPreviousCrop());
        existing.setNextCrop(details.getNextCrop());
        existing.setRotationDate(details.getRotationDate());
        existing.setSeason(details.getSeason());

        return repository.save(existing);
    }

    public boolean delete(Long id, Long userId) {
        RotationRecord record = repository
                .findByIdAndUser_Id(id, userId)
                .orElse(null);

        if (record == null) {
            return false;
        }

        repository.delete(record);
        return true;
    }

    public void deleteAll(Long userId) {
        repository.deleteAll(repository.findByUser_Id(userId));
    }

    public List<RotationRecord> search(String fieldName, Long userId) {
        findUser(userId);

        return repository
                .findByUser_IdAndFieldNameContainingIgnoreCase(
                        userId, fieldName);
    }

    public long count(Long userId) {
        return getAll(userId).size();
    }
}
