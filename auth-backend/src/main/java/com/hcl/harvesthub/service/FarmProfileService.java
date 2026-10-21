
package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.FarmProfile;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.FarmProfileRepository;
import com.hcl.harvesthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FarmProfileService {

    @Autowired
    private FarmProfileRepository repository;

    @Autowired
    private UserRepository userRepository;

    public FarmProfile save(FarmProfile farmProfile, Long userId) {
        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        farmProfile.setUser(user);
        return repository.save(farmProfile);
    }

    public List<FarmProfile> saveAll(
            List<FarmProfile> farmProfiles, Long userId) {

        List<FarmProfile> savedProfiles = new ArrayList<>();

        for (FarmProfile profile : farmProfiles) {
            FarmProfile saved = save(profile, userId);

            if (saved != null) {
                savedProfiles.add(saved);
            }
        }

        return savedProfiles;
    }

    public List<FarmProfile> getAll(Long userId) {
        return repository.findByUser_Id(userId);
    }

    public FarmProfile getById(Long id, Long userId) {
        FarmProfile profile = repository.findById(id).orElse(null);

        if (profile != null &&
                profile.getUser() != null &&
                profile.getUser().getId().equals(userId)) {
            return profile;
        }

        return null;
    }

    public FarmProfile update(
            Long id, FarmProfile farmProfile, Long userId) {

        FarmProfile existing = repository.findById(id).orElse(null);

        if (existing == null ||
                existing.getUser() == null ||
                !existing.getUser().getId().equals(userId)) {
            return null;
        }

        farmProfile.setId(id);
        farmProfile.setUser(existing.getUser());

        return repository.save(farmProfile);
    }

    public String delete(Long id, Long userId) {
        FarmProfile profile = repository.findById(id).orElse(null);

        if (profile == null ||
                profile.getUser() == null ||
                !profile.getUser().getId().equals(userId)) {
            return "Farm profile not found";
        }

        repository.deleteById(id);
        return "Farm profile deleted successfully";
    }

    public void deleteAll(Long userId) {
        List<FarmProfile> profiles = repository.findByUser_Id(userId);
        repository.deleteAll(profiles);
    }

    public List<FarmProfile> search(String farmName, Long userId) {
        return repository
                .findByUser_IdAndFarmNameContainingIgnoreCase(
                        userId, farmName
                );
    }
}
