package com.rusoft.carsharing.repository;

import com.rusoft.carsharing.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByModelAndProductionYear(String model, String year);
    Car findByAndModel(String model);
}
