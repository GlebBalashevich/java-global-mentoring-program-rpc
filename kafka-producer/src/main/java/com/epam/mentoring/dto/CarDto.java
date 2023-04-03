package com.epam.mentoring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CarDto {

    @NotBlank
    private String id;

    @Positive
    private Integer numberOfSeats;

    @NotBlank
    private String engineType;

}
