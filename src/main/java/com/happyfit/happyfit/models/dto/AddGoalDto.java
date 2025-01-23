package com.happyfit.happyfit.models.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddGoalDto {

    @NotNull
    private Float caloricIntake;

    @NotNull
    private Float proteinIntake;

    @NotNull
    private Float carbIntake;

    @NotNull
    private Float fatIntake;
}
