package com.happyfit.happyfit.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "goal_table")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // para a ingestão calórica e macronutrientes
    @Column(nullable = true)
    private Float caloricIntake;

    @Column(nullable = true)
    private Float proteinIntake;

    @Column(nullable = true)
    private Float fatIntake;

    @Column(nullable = true)
    private Float carbIntake;

    @OneToOne(mappedBy = "goal")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Profile profile;
}