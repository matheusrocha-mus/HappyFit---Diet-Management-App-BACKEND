package com.happyfit.happyfit.models.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MealDto {
    @NotNull
    private String name;
    @NotNull
    private List<FoodDto> foods;
}
