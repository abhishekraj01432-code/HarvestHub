
package com.hcl.harvesthub.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ROTATION_RECORDS")
public class RotationRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fieldName;
    private String previousCrop;
    private String nextCrop;
    private LocalDate rotationDate;
    private String season;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public RotationRecord() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFieldName() { return fieldName; }
    public void setFieldName(String fieldName) { this.fieldName = fieldName; }

    public String getPreviousCrop() { return previousCrop; }
    public void setPreviousCrop(String previousCrop) { this.previousCrop = previousCrop; }

    public String getNextCrop() { return nextCrop; }
    public void setNextCrop(String nextCrop) { this.nextCrop = nextCrop; }

    public LocalDate getRotationDate() { return rotationDate; }
    public void setRotationDate(LocalDate rotationDate) { this.rotationDate = rotationDate; }

    public String getSeason() { return season; }
    public void setSeason(String season) { this.season = season; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
