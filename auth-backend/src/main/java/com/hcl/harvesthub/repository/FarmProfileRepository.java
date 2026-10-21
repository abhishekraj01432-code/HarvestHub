
package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.FarmProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmProfileRepository
        extends JpaRepository<FarmProfile, Long> {

    List<FarmProfile> findByUser_Id(Long userId);

    List<FarmProfile> findByUser_IdAndFarmNameContainingIgnoreCase(
            Long userId,
            String farmName
    );
}
