package com.hcl.harvesthub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CROPS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Crop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cropName;
    private String cropType;
    private String season;
    private String duration;

    public void setId(Long id) {
        this.id = id;
    }
}