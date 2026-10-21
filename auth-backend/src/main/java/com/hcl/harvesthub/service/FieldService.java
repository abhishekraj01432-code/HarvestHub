
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Field;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.FieldRepository;
import com.hcl.harvesthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FieldService {

    @Autowired
    private FieldRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Field save(Field field, Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        field.setUser(user);
        return repository.save(field);
    }

    public List<Field> saveAll(List<Field> fields, Long userId) {
        List<Field> savedFields = new ArrayList<>();

        for (Field field : fields) {
            Field saved = save(field, userId);

            if (saved != null) {
                savedFields.add(saved);
            }
        }

        return savedFields;
    }

    public List<Field> getAll(Long userId) {
        return repository.findByUser_Id(userId);
    }

    public Field getById(Long id, Long userId) {
        Field field = repository.findById(id).orElse(null);

        if (field != null &&
                field.getUser() != null &&
                field.getUser().getId().equals(userId)) {
            return field;
        }

        return null;
    }

    public Field update(Long id, Field field, Long userId) {
        Field existingField = repository.findById(id).orElse(null);

        if (existingField == null ||
                existingField.getUser() == null ||
                !existingField.getUser().getId().equals(userId)) {
            return null;
        }

        field.setId(id);
        field.setUser(existingField.getUser());

        return repository.save(field);
    }

    public String delete(Long id, Long userId) {
        Field field = repository.findById(id).orElse(null);

        if (field == null ||
                field.getUser() == null ||
                !field.getUser().getId().equals(userId)) {
            return "Field not found";
        }

        repository.deleteById(id);
        return "Field deleted successfully";
    }

    public void deleteAll(Long userId) {
        List<Field> fields = repository.findByUser_Id(userId);
        repository.deleteAll(fields);
    }

    public List<Field> search(String fieldName, Long userId) {
        return repository
                .findByUser_IdAndFieldNameContainingIgnoreCase(
                        userId, fieldName
                );
    }
}
