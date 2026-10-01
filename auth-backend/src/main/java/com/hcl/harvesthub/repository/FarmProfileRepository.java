package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.FarmProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmProfileRepository extends JpaRepository<FarmProfile, Long> {

    List<FarmProfile> findByFarmNameContainingIgnoreCase(String farmName);

}