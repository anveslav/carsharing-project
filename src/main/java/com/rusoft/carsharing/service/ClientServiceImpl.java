package com.rusoft.carsharing.service;

import com.rusoft.carsharing.model.Car;
import com.rusoft.carsharing.model.Client;
import com.rusoft.carsharing.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final CarService carService;

    @Override
    public Client addClient(String clientName, String birthYear,
                            String carModel, String carYear) {

        Optional<Client> optionalClient = findClientByNameAndBirthYear(clientName, birthYear);
        Optional<Car> optionalCar = carService.getFreeCarByModelAndYear(carModel, carYear);
        if (!optionalClient.isPresent() && optionalCar.isPresent()) {
            Car car = optionalCar.get();
            Client client = Client.builder()
                    .name(clientName)
                    .birthYear(birthYear)
                    .car(car)
                    .build();
            car.setClient(client);
            clientRepository.save(client);
            carService.saveCar(car);
            return client;
        }
        return optionalClient.get();
    }

    @Override
    public Optional<Client> findClientByNameAndBirthYear(String name, String year) {
        Client client = clientRepository.findClientByNameAndBirthYear(name, year);
        if (client != null){
            return Optional.of(client);
        }
        return Optional.empty();
    }

    @Override
    public void deleteClient(String clientName, String carModel) {
        Client client = clientRepository.findClientByName(clientName);
        if (client != null) {

        }

    }
}

