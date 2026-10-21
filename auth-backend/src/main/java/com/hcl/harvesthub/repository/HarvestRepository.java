
package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    List<Harvest> findByUser_Id(Long userId);

    List<Harvest> findByUser_IdAndQualityContainingIgnoreCase(
            Long userId, String quality);

    List<Harvest> findByUser_IdAndCropNameContainingIgnoreCase(
            Long userId, String cropName);
}
