package com.rusoft.carsharing.service;

import com.rusoft.carsharing.dto.CarDto;
import com.rusoft.carsharing.exception.CarsharingException;
import com.rusoft.carsharing.mapper.ModelMapper;
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
    public Optional<Car> getFreeCar(String model, String year) {
        try {
            Optional<Car> optionalCar = getCarByModelAndYear(model, year);
            if (optionalCar.isPresent()) {
                Car car = optionalCar.get();
                if (!carIsBusyByClient(car)) {
                    return Optional.of(car);
                }
            }
            return Optional.empty();
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, GETTING_FREE_CAR_EXCEPTION);
        }
    }

    @Override
    public Optional<Car> getCarByModelAndYear(String model, String year) {
        try {
            Car car = carRepository.findByModelAndProductionYear(model, year);
            if (car != null) {
                return Optional.of(car);
            }
            return Optional.empty();
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, GETTING_CAR_BY_MODEL_AND_YEAR_EXCEPTION);
        }
    }

    @Override
    public Optional<Car> getCarByModel(String model) {
        try {
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
    public void addCar(CarDto car) {
        try {
            Optional<Car> optionalCar = getCarByModelAndYear(car.getModel(), car.getProductionYear());
            if (!optionalCar.isPresent()) {
                carRepository.save(ModelMapper.carDtoToCar(car));
            }
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
        if (car.getClient() == null) {
            isBusy = false;
        }
        return isBusy;
    }
}
