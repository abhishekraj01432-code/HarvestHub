package com.hcl.harvesthub.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "EQUIPMENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String equipmentName;
    private String equipmentType;
    private String model;
    private String purchaseDate;
    private String status;

    public void setId(Long id) {
        this.id = id;
    }
}