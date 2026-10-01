package com.hcl.harvesthub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "FIELDS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;
    private Double area;
    private String soilType;
    private String irrigationType;
    private String location;
    private String status;

    public void setId(Long id) {
        this.id = id;
    }
}