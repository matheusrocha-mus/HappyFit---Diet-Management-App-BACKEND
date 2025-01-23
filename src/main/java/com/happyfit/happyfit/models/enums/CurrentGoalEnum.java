package com.happyfit.happyfit.models.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrentGoalEnum {

    HIPERTROFIA(1, "HIPERTROFIA"),
    EMAGRECIMENTO(2, "EMAGRECIMENTO"),
    MANUTENCAO(3, "MANUTENCAO");

    private Integer code;
    private String description;

    public static CurrentGoalEnum toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (CurrentGoalEnum x : CurrentGoalEnum.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }

}