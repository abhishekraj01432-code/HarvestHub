package com.hcl.harvesthub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FARM_PROFILES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FarmProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String farmName;
    private String ownerName;
    private String location;
    private Double totalArea;
    private String areaUnit;

    public void setId(Long id) {
        this.id = id;
    }
}