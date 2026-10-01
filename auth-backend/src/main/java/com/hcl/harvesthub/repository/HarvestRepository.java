package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HarvestRepository extends JpaRepository<Harvest, Long> {

    List<Harvest> findByQualityContainingIgnoreCase(String quality);

    List<Harvest> findByCropNameContainingIgnoreCase(String cropName);
}