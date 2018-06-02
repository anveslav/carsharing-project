package com.rusoft.carsharing.repository;

import com.rusoft.carsharing.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findClientByNameAndBirthYear(String name, String year);

    Client findClientByName(String name);

}
