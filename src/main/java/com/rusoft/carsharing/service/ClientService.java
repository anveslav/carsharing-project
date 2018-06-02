package com.rusoft.carsharing.service;

import com.rusoft.carsharing.dto.ClientDto;
import com.rusoft.carsharing.model.Client;

import java.util.Optional;

public interface ClientService {

    ClientDto addClient(String clientName, String birthYear, String carModel,
                        String carYear);

    Optional<Client> getClientByNameAndBirthYear(String name, String year);

    Optional<Client> getClientByName(String name);

    void deleteClient(String clientName, String carModel);

}
