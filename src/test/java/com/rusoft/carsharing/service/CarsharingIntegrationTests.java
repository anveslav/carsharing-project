package com.rusoft.carsharing.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rusoft.carsharing.configuration.AppConfig;
import com.rusoft.carsharing.configuration.TestDataConfig;
import com.rusoft.carsharing.exception.CarsharingException;
import com.rusoft.carsharing.model.Car;
import com.rusoft.carsharing.model.Client;
import com.rusoft.carsharing.repository.ClientRepository;
import com.rusoft.carsharing.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDataConfig.class, AppConfig.class},
        loader = AnnotationConfigContextLoader.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CarsharingIntegrationTests {

    private static final String CLIENT = "data/client.json";
    private static final String BUSY_CAR = "data/carBusy.json";

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ClientRepository clientRepository;

    private Client client;
    private Car busyCar;
    private String name;
    private String birthYear;
    private String carModel;
    private String carProductionYear;

    @Before
    public void setUp() throws Exception {
        name = "Ivan Ivanov";
        birthYear = "1997";
        carModel = "BMV";
        carProductionYear = "2011";
        client = TestUtils.readJson(CLIENT, new TypeReference<Client>() {
        });
        busyCar = TestUtils.readJson(BUSY_CAR, new TypeReference<Car>() {
        });
    }

    @Test
    public void shouldAddClientInDatabaseIfHeDoesNotExist() {
        //when
        clientService.addClient(name, birthYear, carModel, carProductionYear);
        Client actualClient = clientRepository.findClientByNameAndBirthYear(name, birthYear);

        //then
        assertEquals(client, actualClient);
    }

    @Test
    public void shouldDeleteClientInDatabaseIfHeExists() {
        //when
        clientService.deleteClient(name, carModel);
        Client actualClient = clientRepository.findClientByName(name);

        //then
        assertNull(actualClient);
    }

    @Test(expected = CarsharingException.class)
    public void shouldThrowExceptionIfClientExistsInDatabase() {
        //when
        clientRepository.save(client);
        clientService.addClient(name, birthYear, carModel, carProductionYear);
    }
}