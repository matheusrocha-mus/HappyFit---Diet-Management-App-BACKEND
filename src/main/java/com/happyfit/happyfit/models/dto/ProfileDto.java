package com.happyfit.happyfit.models.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProfileDto {

    @NotNull
    private Float weight;

    @NotNull
    private Float height;

    @NotNull
    private Integer age;

    @NotNull
    private Integer gender;

    @NotNull
    private Float hip;

    @NotNull
    private Float waist;

    @NotNull
    private Float neck;

    @NotNull
    private Integer currentGoal;

    @NotNull
    private Float pal;

    private Float caloricIntake;
    private Float proteinIntake;
    private Float fatIntake;
    private Float carbIntake;

}