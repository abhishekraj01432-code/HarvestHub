package com.hcl.harvesthub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "CROP_PLANS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CropPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;
    private String cropName;
    private LocalDate sowingDate;
    private LocalDate expectedHarvestDate;
    private String status;

    public void setId(Long id) {
        this.id = id;
    }
}