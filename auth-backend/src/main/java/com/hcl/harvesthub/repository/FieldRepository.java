
package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FieldRepository extends JpaRepository<Field, Long> {

    // Get fields belonging to a particular user
    List<Field> findByUser_Id(Long userId);

    // Search fields belonging to a particular user
    List<Field> findByUser_IdAndFieldNameContainingIgnoreCase(
            Long userId,
            String fieldName
    );
}
