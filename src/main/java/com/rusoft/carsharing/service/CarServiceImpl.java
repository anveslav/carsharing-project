package com.rusoft.carsharing.service;

import com.rusoft.carsharing.exception.CarsharingException;
import com.rusoft.carsharing.model.Car;
import com.rusoft.carsharing.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.rusoft.carsharing.exception.errorcode.CarServiceErrorCode.*;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    public Optional<Car> getFreeCarByModelAndYear(String model, String year) {
        try {
            Car car = carRepository.findByModelAndProductionYear(model, year);
            if (!carIsBusyByClient(car)) {
                return Optional.of(car);
            }
            return Optional.empty();
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, GETTING_CAR_BY_MODEL_AND_YEAR_EXCEPTION);
        }
    }

    @Override
    public Optional<Car> getCarByModel(String model) {
        try{
            Car car = carRepository.findByModel(model);
            if (car != null) {
                return Optional.of(car);
            }
            return Optional.empty();
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, GETTING_CAR_BY_MODEL_EXCEPTION);
        }
    }

    @Override
    public Car saveCar(Car car) {
        try {
            return carRepository.save(car);
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, SAVING_CAR_EXCEPTION);
        }
    }

    @Override
    public void makeCarFree(String model) {
        try {
            Optional<Car> optionalCar = getCarByModel(model);
            if (optionalCar.isPresent()) {
                Car car = optionalCar.get();
                car.setClient(null);
                carRepository.save(car);
            }
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, MAKING_CAR_FREE_EXCEPTION);
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
