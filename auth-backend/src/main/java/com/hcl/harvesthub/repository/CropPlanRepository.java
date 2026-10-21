
package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.CropPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropPlanRepository
        extends JpaRepository<CropPlan, Long> {

    List<CropPlan> findByUser_Id(Long userId);

    List<CropPlan> findByUser_IdAndCropNameContainingIgnoreCase(
            Long userId,
            String cropName
    );
}
