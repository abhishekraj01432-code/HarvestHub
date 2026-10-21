
package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByUser_Id(Long userId);

    List<Activity> findByUser_IdAndActivityNameContainingIgnoreCase(
            Long userId,
            String activityName
    );
}
