package com.rusoft.carsharing.service;

import com.rusoft.carsharing.dto.ClientDto;
import com.rusoft.carsharing.exception.CarsharingException;
import com.rusoft.carsharing.mapper.ModelMapper;
import com.rusoft.carsharing.model.Car;
import com.rusoft.carsharing.model.Client;
import com.rusoft.carsharing.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.rusoft.carsharing.exception.errorcode.ClientServiceErrorCode.*;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CarService carService;

    @Override
    public ClientDto addClient(String clientName, String birthYear,
                               String carModel, String carYear) {
        try {
            Optional<Client> optionalClient = getClientByNameAndBirthYear(clientName, birthYear);
            Optional<Car> optionalCar = carService.getFreeCar(carModel, carYear);
            if (!optionalClient.isPresent() && optionalCar.isPresent()) {
                Car car = optionalCar.get();
                Client client = Client.builder()
                        .name(clientName)
                        .birthYear(birthYear)
                        .car(car)
                        .build();
                car.setClient(client);
                clientRepository.save(client);
                //carService.save(car);
                return ModelMapper.clientToClientDto(client);
            }
            return ModelMapper.clientToClientDto(optionalClient.get());
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, ADDING_CLIENT_EXCEPTION);
        }
    }

    @Override
    public Optional<Client> getClientByNameAndBirthYear(String name, String year) {
        try {
            Client client = clientRepository.findClientByNameAndBirthYear(name, year);
            if (client != null) {
                return Optional.of(client);
            }
            return Optional.empty();
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, GETTING_CLIENT_BY_NAME_AND_BIRTH_YEAR_EXCEPTION);
        }
    }

    @Override
    public Optional<Client> getClientByName(String name) {
        try {
            Client client = clientRepository.findClientByName(name);
            if (client != null) {
                return Optional.of(client);
            }
            return Optional.empty();
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, GETTING_CLIENT_BY_NAME_EXCEPTION);
        }
    }

    @Override
    public void deleteClient(String clientName, String carModel) {
        try {
            Optional<Client> optionalClient = getClientByName(clientName);
            Optional<Car> optionalCar = carService.getCarByModel(carModel);
            if (optionalCar.isPresent() && optionalClient.isPresent()) {
                Car car = optionalCar.get();
                Client client = optionalClient.get();
                if (carBelongsToClient(car, client)) {
                    carService.makeCarFree(carModel);
                    clientRepository.delete(client);
                }
            }
        } catch (Exception ex) {
            throw CarsharingException.wrap(ex, DELETING_CLIENT_EXCEPTION);
        }
    }

    private boolean carBelongsToClient(Car car, Client client) {
        return car.getClient().getId().equals(client.getId());
    }
}

