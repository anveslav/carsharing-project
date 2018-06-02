package com.rusoft.carsharing.service;

import com.rusoft.carsharing.model.Car;
import com.rusoft.carsharing.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Optional<Car> getFreeCarByModelAndYear(String model, String year) {
        Car car = carRepository.findByModelAndProductionYear(model, year);
        if (!carIsBusyByClient(car)) {
            return Optional.of(car);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Car> getCarByModel(String model) {
        Car car = carRepository.findByModel(model);
        if (car != null) {
            return Optional.of(car);
        }
        return Optional.empty();
    }

    @Override
    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public void makeCarFree(String model) {
        Optional<Car> optionalCar = getCarByModel(model);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            car.setClient(null);
            carRepository.save(car);
        }
    }

    private boolean carIsBusyByClient(Car car) {
        boolean isBusy = true;
        if (car != null) {
            if (car.getClient() == null) {
                isBusy = false;
            }
        }
        return isBusy;
    }


}
