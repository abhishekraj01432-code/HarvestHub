package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

    List<Crop> findByCropNameContainingIgnoreCase(String cropName);
}