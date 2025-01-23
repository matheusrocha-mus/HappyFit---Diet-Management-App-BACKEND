package com.happyfit.happyfit.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.happyfit.happyfit.models.enums.CurrentGoalEnum;
import com.happyfit.happyfit.models.enums.GenderEnum;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "profile_table")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Float weight;

    @Column(nullable = false)
    private Float height;

    @Column(nullable = false)
    private Integer age;

    @Column(nullable = false)
    private Integer gender;

    public GenderEnum getGender() {
        return GenderEnum.toEnum(gender);
    }

    public void addGender(Integer genderEnum) {
        this.gender = genderEnum;
    }

    @Column(nullable = false)
    private Float hip;

    @Column(nullable = false)
    private Float waist;

    @Column(nullable = false)
    private Float neck;

    @Column(nullable = false)
    private Integer currentGoal;

    public CurrentGoalEnum getCurrentGoal() {
        return CurrentGoalEnum.toEnum(currentGoal);
    }

    public void addCurrentGoal(Integer currentGoalEnum) {
        this.currentGoal = currentGoalEnum;
    }

    @Column(nullable = false)
    private Float pal;

    @OneToOne(mappedBy = "profile")
    @JsonProperty(access = Access.WRITE_ONLY)
    @EqualsAndHashCode.Exclude
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "goal_id", referencedColumnName = "id")
    private Goal goal;
}