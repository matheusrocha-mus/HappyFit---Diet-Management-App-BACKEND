package com.happyfit.happyfit.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "diet_table")
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Float totalCalories;

    @Column(nullable = false)
    private Float totalCarbs;

    @Column(nullable = false)
    private Float totalProteins;

    @Column(nullable = false)
    private Float totalFats;

    @OneToOne(mappedBy = "diet")
    @JsonProperty(access = Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    private User user;

    @OneToMany(mappedBy = "diet")
    @JsonProperty(access = Access.READ_WRITE)
    private List<Meal> meals = new ArrayList<Meal>();
}