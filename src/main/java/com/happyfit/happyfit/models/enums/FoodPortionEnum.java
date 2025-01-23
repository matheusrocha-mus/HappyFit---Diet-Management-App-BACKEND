package com.happyfit.happyfit.models.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FoodPortionEnum {
    UNIDADE(1, "1 unidade"),
    GRAMAS(2, "100 g");

    private Integer code;
    private String description;

    public static FoodPortionEnum toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (FoodPortionEnum x : FoodPortionEnum.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }
}
