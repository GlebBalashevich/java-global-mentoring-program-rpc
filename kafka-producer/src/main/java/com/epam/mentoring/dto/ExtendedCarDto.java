package com.epam.mentoring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExtendedCarDto {

    @NotBlank
    private String id;

    @Positive
    private Integer numberOfSeats;

    @NotNull
    private EngineType engineType;

    @NotNull
    private Type type;

    public enum EngineType {
        DIESEL, GASOLINE, ELECTRIC
    }

    public enum Type {
        SEDAN, SUV, MINIVAN
    }

}
