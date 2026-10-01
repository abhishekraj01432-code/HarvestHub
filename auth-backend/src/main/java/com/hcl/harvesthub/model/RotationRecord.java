package com.hcl.harvesthub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "ROTATION_RECORDS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RotationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;
    private String previousCrop;
    private String nextCrop;
    private LocalDate rotationDate;
    private String season;

    public void setId(Long id) {
        this.id = id;
    }
}