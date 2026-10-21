package com.hcl.harvesthub.service;

import com.hcl.harvesthub.model.Crop;
import com.hcl.harvesthub.model.User;
import com.hcl.harvesthub.repository.CropRepository;
import com.hcl.harvesthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropService {

    @Autowired
    private CropRepository repository;

    @Autowired
    private UserRepository userRepository;

    // Insert
    public Crop save(Crop crop, Long userId) {

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return null;
        }

        crop.setUser(user);

        return repository.save(crop);
    }

    // Read All - User Wise
    public List<Crop> getAll(Long userId) {
        return repository.findByUser_Id(userId);
    }

    // Get By ID - User Wise
    public Crop getById(Long id, Long userId) {

        Crop crop = repository.findById(id).orElse(null);

        if (crop != null &&
                crop.getUser() != null &&
                crop.getUser().getId().equals(userId)) {
            return crop;
        }

        return null;
    }

    // Update - User Wise
    public Crop update(Long id, Crop crop, Long userId) {

        Crop existingCrop = repository.findById(id).orElse(null);

        if (existingCrop == null ||
                existingCrop.getUser() == null ||
                !existingCrop.getUser().getId().equals(userId)) {
            return null;
        }

        crop.setId(id);
        crop.setUser(existingCrop.getUser());

        return repository.save(crop);
    }

    // Delete - User Wise
    public String delete(Long id, Long userId) {

        Crop crop = repository.findById(id).orElse(null);

        if (crop == null) {
            return "Crop not found";
        }

        if (crop.getUser() == null ||
                !crop.getUser().getId().equals(userId)) {
            return "Crop not found";
        }

        repository.deleteById(id);

        return "Crop deleted successfully";
    }

    // Delete All - User Wise
    public void deleteAll(Long userId) {

        List<Crop> crops = repository.findByUser_Id(userId);

        repository.deleteAll(crops);
    }

    // Search - User Wise
    public List<Crop> search(String cropName, Long userId) {

        return repository.findByUser_IdAndCropNameContainingIgnoreCase(
                userId,
                cropName
        );
    }
}