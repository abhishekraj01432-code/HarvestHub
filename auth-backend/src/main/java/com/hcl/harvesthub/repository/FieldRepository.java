package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    List<Field> findByFieldNameContainingIgnoreCase(String fieldName);
}