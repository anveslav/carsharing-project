package com.rusoft.carsharing.service;

import com.rusoft.carsharing.dto.CarDto;
import com.rusoft.carsharing.model.Car;

import java.util.Optional;

public interface CarService {

    Optional<Car> getFreeCarByModelAndYear(String model, String year);

    Optional<Car> getCarByModelAndYear(String model, String year);

    Optional<Car> getCarByModel(String model);

    void addCar(CarDto car);

    void makeCarFree(String model);
}
