package com.hcl.harvesthub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "EXPENSES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String expenseType;
    private Double amount;
    private LocalDate expenseDate;
    private String description;

    public void setId(Long id) {
        this.id = id;
    }


    public Double getAmount() {
        return amount;
    }

}