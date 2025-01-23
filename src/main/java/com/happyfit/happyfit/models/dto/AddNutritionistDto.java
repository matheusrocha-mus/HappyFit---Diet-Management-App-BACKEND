package com.happyfit.happyfit.models.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddNutritionistDto {

    @NotNull
    private Integer nutritionistId;

}