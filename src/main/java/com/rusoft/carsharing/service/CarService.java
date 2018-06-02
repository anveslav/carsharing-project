package com.rusoft.carsharing.service;

import com.rusoft.carsharing.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCars();

    Optional<Car> getFreeCarByModelAndYear(String model, String year);

    Car getCarByModel(String model);

    Car getCarById(Long id);

    Car saveCar(Car car);

    void updateCar(Car car);

    void deleteCarById(Long id);

    boolean carIsBusyByClient(Car car);

}
