package com.happyfit.happyfit.models.enums;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {

    USER(1, "USER"),
    NUTRITIONIST(2, "NUTRITIONIST");

    private Integer code;
    private String description;

    public static RoleEnum toEnum(Integer code) {
        if (Objects.isNull(code))
            return null;

        for (RoleEnum x : RoleEnum.values()) {
            if (code.equals(x.getCode()))
                return x;
        }

        throw new IllegalArgumentException("Invalid code: " + code);
    }

}