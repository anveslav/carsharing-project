package com.rusoft.carsharing.controller;

import com.rusoft.carsharing.dto.CarDto;
import com.rusoft.carsharing.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public void addCar(@RequestBody CarDto car) {
        carService.addCar(car);
    }
}
