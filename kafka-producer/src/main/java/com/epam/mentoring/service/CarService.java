package com.epam.mentoring.service;

import com.epam.mentoring.dto.CarDto;
import com.epam.mentoring.dto.ExtendedCarDto;
import com.epam.mentoring.mapper.CarMapper;
import com.epam.mentoring.producer.CarEventProducer;
import com.epam.mentoring.producer.ExtendedCarEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarMapper carMapper;

    private final CarEventProducer carEventProducer;

    private final ExtendedCarEventProducer extendedCarEventProducer;

    public Mono<CarDto> addCar(CarDto carDto){
        final var car = carMapper.toCar(carDto);
        return carEventProducer.sendCarEvent(car)
                .map(carMapper::toCarDto);
    }

    public Mono<ExtendedCarDto> addExtendedCar(ExtendedCarDto extendedCarDto){
        final var extendedCar = carMapper.toExtendedCar(extendedCarDto);
        return extendedCarEventProducer.sendCarEvent(extendedCar)
                .map(carMapper::toExtendedCarDto);
    }
}
