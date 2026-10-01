package com.hcl.harvesthub.repository;

import com.hcl.harvesthub.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByActivityNameContainingIgnoreCase(String activityName);

}