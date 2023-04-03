package com.epam.mentoring.mapper;

import com.epam.mentoring.dto.CarDto;
import com.epam.mentoring.dto.ExtendedCarDto;
import com.epam.mentoring.model.Car;
import com.epam.mentoring.model.ExtendedCar;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    Car toCar(CarDto carDto);

    CarDto toCarDto(Car car);

    ExtendedCar toExtendedCar(ExtendedCarDto extendedCarDto);

    ExtendedCarDto toExtendedCarDto(ExtendedCar extendedCar);
}
