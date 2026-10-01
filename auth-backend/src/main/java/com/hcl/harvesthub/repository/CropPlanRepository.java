package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.CropPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CropPlanRepository extends JpaRepository<CropPlan, Long> {

    List<CropPlan> findByCropNameContainingIgnoreCase(String cropName);

}