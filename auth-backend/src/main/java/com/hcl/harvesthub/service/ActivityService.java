
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Activity;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.ActivityRepository;
import com.hcl.harvesthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repository;

    @Autowired
    private UserRepository userRepository;

    public Activity save(Activity activity, Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        activity.setUser(user);
        return repository.save(activity);
    }

    public List<Activity> saveAll(
            List<Activity> activities, Long userId) {

        List<Activity> savedActivities = new ArrayList<>();

        for (Activity activity : activities) {
            Activity saved = save(activity, userId);

            if (saved != null) {
                savedActivities.add(saved);
            }
        }

        return savedActivities;
    }

    public List<Activity> getAll(Long userId) {
        return repository.findByUser_Id(userId);
    }

    public Activity getById(Long id, Long userId) {
        Activity activity = repository.findById(id).orElse(null);

        if (activity != null
                && activity.getUser() != null
                && activity.getUser().getId().equals(userId)) {
            return activity;
        }

        return null;
    }

    public Activity update(
            Long id, Activity activity, Long userId) {

        Activity existing = repository.findById(id).orElse(null);

        if (existing == null
                || existing.getUser() == null
                || !existing.getUser().getId().equals(userId)) {
            return null;
        }

        activity.setId(id);
        activity.setUser(existing.getUser());

        return repository.save(activity);
    }

    public String delete(Long id, Long userId) {
        Activity activity = repository.findById(id).orElse(null);

        if (activity == null
                || activity.getUser() == null
                || !activity.getUser().getId().equals(userId)) {
            return "Activity not found";
        }

        repository.deleteById(id);
        return "Activity deleted successfully";
    }

    public void deleteAll(Long userId) {
        List<Activity> activities = repository.findByUser_Id(userId);
        repository.deleteAll(activities);
    }

    public List<Activity> search(String activityName, Long userId) {
        return repository
                .findByUser_IdAndActivityNameContainingIgnoreCase(
                        userId, activityName
                );
    }
}
