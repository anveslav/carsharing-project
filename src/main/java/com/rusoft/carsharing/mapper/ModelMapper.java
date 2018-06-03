package com.rusoft.carsharing.mapper;

import com.rusoft.carsharing.dto.CarDto;
import com.rusoft.carsharing.dto.ClientDto;
import com.rusoft.carsharing.model.Car;
import com.rusoft.carsharing.model.Client;

public class ModelMapper {

    public static Car carDtoToCar(CarDto carDto) {
        return Car.builder()
                .model(carDto.getModel())
                .productionYear(carDto.getProductionYear())
                .build();
    }

    public static Client clientDtoToClient(ClientDto clientDto) {
        return Client.builder()
                .name(clientDto.getName())
                .birthYear(clientDto.getBirthYear())
                .build();
    }

    public static CarDto carToCarDto(Car car) {
        return CarDto.builder()
                .model(car.getModel())
                .productionYear(car.getProductionYear())
                .client(car.getClient())
                .build();
    }

    public static ClientDto clientToClientDto(Client client) {
        return ClientDto.builder()
                .name(client.getName())
                .birthYear(client.getBirthYear())
                .car(carToCarDto(client.getCar()))
                .build();
    }
}
