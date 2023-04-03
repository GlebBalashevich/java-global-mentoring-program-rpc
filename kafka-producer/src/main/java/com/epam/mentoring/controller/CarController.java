package com.epam.mentoring.controller;

import com.epam.mentoring.dto.CarDto;
import com.epam.mentoring.dto.ExtendedCarDto;
import com.epam.mentoring.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/cars")
@RestController
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public Mono<CarDto> addCar(@RequestBody CarDto carDto){
        return carService.addCar(carDto);
    }

    @PostMapping("/extended")
    public Mono<ExtendedCarDto> addExtendedCar(@RequestBody ExtendedCarDto extendedCarDto){
        return carService.addExtendedCar(extendedCarDto);
    }

}
