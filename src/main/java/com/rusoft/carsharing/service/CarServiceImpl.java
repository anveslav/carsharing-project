package com.rusoft.carsharing.service;

import com.rusoft.carsharing.model.Car;
import com.rusoft.carsharing.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public Optional<Car> getFreeCarByModelAndYear(String model, String year) {
        Car car = carRepository.findByModelAndProductionYear(model, year);
        if (!carIsBusyByClient(car)) {
            return Optional.of(car);
        }
        return Optional.empty();
    }

    @Override
    public Car getCarByModel(String model) {
        return carRepository.findByAndModel(model);
    }


    @Override
    public Car getCarById(Long id) {
        return null;
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void updateCar(Car car) {

    }

    @Override
    public void deleteCarById(Long id) {

    }

    @Override
    public boolean carIsBusyByClient(Car car) {
        boolean isBusy = true;
        if (car.getClient() == null) {
            isBusy = false;
        }
        return isBusy;
    }
}
