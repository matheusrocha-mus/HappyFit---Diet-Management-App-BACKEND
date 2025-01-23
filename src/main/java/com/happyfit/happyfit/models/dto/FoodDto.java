package com.happyfit.happyfit.models.dto;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FoodDto {

    private Integer id;

    @NotNull
    private Integer foodOption;

    @NotNull
    private double quantity;
}
