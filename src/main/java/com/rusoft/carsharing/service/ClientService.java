package com.rusoft.carsharing.service;

import com.rusoft.carsharing.model.Client;

import java.util.Optional;

public interface ClientService {

    Client addClient(String clientName, String birthYear, String carModel,
                     String carYear);

    Optional<Client> findClientByNameAndBirthYear(String name, String year);

    void deleteClient(String clientName, String carModel);


}
