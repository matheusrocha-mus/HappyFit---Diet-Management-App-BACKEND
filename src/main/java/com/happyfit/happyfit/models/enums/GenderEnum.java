package com.happyfit.happyfit.models.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum {

    MASCULINO(1, "MASCULINO"),
    FEMININO(2, "FEMININO");

    private Integer code;
    private String description;

    public static GenderEnum toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (GenderEnum x : GenderEnum.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }

}