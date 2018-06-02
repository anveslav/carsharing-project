package com.rusoft.carsharing.service;

import com.rusoft.carsharing.model.Car;

import java.util.Optional;

public interface CarService {


    Optional<Car> getFreeCarByModelAndYear(String model, String year);

    Optional<Car> getCarByModel(String model);

    Car saveCar(Car car);

    void makeCarFree(String model);
}
