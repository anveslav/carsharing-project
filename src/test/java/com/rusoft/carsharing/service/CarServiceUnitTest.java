package com.rusoft.carsharing.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rusoft.carsharing.model.Car;
import com.rusoft.carsharing.repository.CarRepository;
import com.rusoft.carsharing.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceUnitTest {

    private static final String FREE_CAR = "data/carFree.json";
    private static final String BUSY_CAR = "data/carBusy.json";
    private String model;
    private String productionYear;
    private Car freeCar;
    private Car busyCar;

    @Mock
    private CarRepository carRepositoryMock;

    @InjectMocks
    private CarServiceImpl carService;

    @Before
    public void setUp() throws Exception {
        model = "BMW";
        productionYear = "2011";
        freeCar = TestUtils.readJson(FREE_CAR, new TypeReference<Car>() {
        });
        busyCar = TestUtils.readJson(BUSY_CAR, new TypeReference<Car>() {
        });
    }

    @Test
    public void shouldReturnFreeCar() {
        //given
        when(carRepositoryMock.findByModelAndProductionYear(model, productionYear))
                .thenReturn(freeCar);

        //when
        Optional<Car> actualFreeCarOptional = carService.getFreeCar(model, productionYear);
        Optional<Car> expectedFreeCarOptional = Optional.of(freeCar);

        //then
        assertEquals(expectedFreeCarOptional, actualFreeCarOptional);
        verify(carRepositoryMock, times(1))
                .findByModelAndProductionYear(model, productionYear);
    }

    @Test
    public void shouldReturnEmptyOptionalIfCarIsBusy() {
        //given
        when(carRepositoryMock.findByModelAndProductionYear(model, productionYear))
                .thenReturn(busyCar);
        //when
        Optional<Car> actualEmptyOptional = carService.getFreeCar(model, productionYear);
        Optional<Car> expectedEmptyOptional = Optional.empty();

        //then
        assertEquals(expectedEmptyOptional, actualEmptyOptional);
        verify(carRepositoryMock, times(1))
                .findByModelAndProductionYear(model, productionYear);
    }

    @Test
    public void shouldReturnCarByModelAndYear() {
        //given
        when(carRepositoryMock.findByModelAndProductionYear(model, productionYear))
                .thenReturn(busyCar);
        //when
        Optional<Car> actualCarOptional = carService.getCarByModelAndYear(model, productionYear);
        Optional<Car> expectedCarOptional = Optional.of(busyCar);

        //then
        assertEquals(expectedCarOptional, actualCarOptional);
        verify(carRepositoryMock, times(1))
                .findByModelAndProductionYear(model, productionYear);
    }

    @Test
    public void shouldReturnEmptyOptionalIfCarIsNotFoundByModelAndYear() {
        //given
        when(carRepositoryMock.findByModelAndProductionYear(model, productionYear))
                .thenReturn(null);

        //when
        Optional<Car> actualEmptyOptional = carService.getCarByModelAndYear(model, productionYear);
        Optional<Car> expectedEmptyOptional = Optional.empty();

        //then
        assertEquals(expectedEmptyOptional, actualEmptyOptional);
        verify(carRepositoryMock, times(1))
                .findByModelAndProductionYear(model, productionYear);
    }

    @Test
    public void shouldReturnCarByModel() {
        //given
        when(carRepositoryMock.findByModel(model))
                .thenReturn(busyCar);
        //when
        Optional<Car> actualCarOptional = carService.getCarByModel(model);
        Optional<Car> expectedCarOptional = Optional.of(busyCar);

        //then
        assertEquals(expectedCarOptional, actualCarOptional);
        verify(carRepositoryMock, times(1))
                .findByModel(model);
    }

    @Test
    public void shouldReturnEmptyOptionalIfCarIsNotFoundByModel() {
        //given
        when(carRepositoryMock.findByModel(model))
                .thenReturn(null);

        //when
        Optional<Car> actualEmptyOptional = carService.getCarByModel(model);
        Optional<Car> expectedEmptyOptional = Optional.empty();

        //then
        assertEquals(expectedEmptyOptional, actualEmptyOptional);
        verify(carRepositoryMock, times(1))
                .findByModel(model);
    }
}