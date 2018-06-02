package com.rusoft.carsharing.service;

import com.rusoft.carsharing.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long id);

    Car saveCar(Car car);

    void updateCar(Car car);

    void deleteCarById(Long id);

}
