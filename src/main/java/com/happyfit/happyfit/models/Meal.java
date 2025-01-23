package com.happyfit.happyfit.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "meal_table")
public class Meal {
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "diet_id")
    @JsonIgnore
    private Diet diet;

    @ManyToOne
    @JoinColumn(name = "diary_id")
    @JsonIgnore
    private Diary diary;

    @Column(length = 100, nullable = false)
    private String name;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    @JsonProperty(access = Access.READ_WRITE)
    private List<Food> foods = new ArrayList<Food>();
}