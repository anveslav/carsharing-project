package com.rusoft.carsharing.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.rusoft.carsharing.model.Client;
import com.rusoft.carsharing.repository.ClientRepository;
import com.rusoft.carsharing.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceUnitTest {

    private static final String CLIENT = "data/client.json";
    private String name;
    private String birthYear;
    private Client client;

    @Mock
    private ClientRepository clientRepositoryMock;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Before
    public void setUp() throws Exception {
        name = "Ivan Ivanov";
        birthYear = "1997";
        client = TestUtils.readJson(CLIENT, new TypeReference<Client>() {
        });
    }

    @Test
    public void shouldReturnClientByNameAndBirthYear() {
        //given
        when(clientRepositoryMock.findClientByNameAndBirthYear(name, birthYear))
                .thenReturn(client);
        //when
        Optional<Client> actualClientOptional = clientService.getClientByNameAndBirthYear(name, birthYear);
        Optional<Client> expectedClientOptional = Optional.of(client);

        //then
        assertEquals(actualClientOptional, expectedClientOptional);
        verify(clientRepositoryMock, times(1))
                .findClientByNameAndBirthYear(name, birthYear);
    }

    @Test
    public void shouldReturnEmptyOptionalIfClientIsNotFoundByNameAndBirthYear(){
        //given
        when(clientRepositoryMock.findClientByNameAndBirthYear(name, birthYear))
                .thenReturn(null);

        //when
        Optional<Client> actualEmptyOptional = clientService.getClientByNameAndBirthYear(name, birthYear);
        Optional<Client> expectedEmptyOptional = Optional.empty();

        //then
        assertEquals(expectedEmptyOptional, actualEmptyOptional);
        verify(clientRepositoryMock, times(1))
                .findClientByNameAndBirthYear(name, birthYear);
    }

    @Test
    public void shouldReturnClientByName() {
        //given
        when(clientRepositoryMock.findClientByName(name))
                .thenReturn(client);
        //when
        Optional<Client> actualClientOptional = clientService.getClientByName(name);
        Optional<Client> expectedClientOptional = Optional.of(client);

        //then
        assertEquals(actualClientOptional, expectedClientOptional);
        verify(clientRepositoryMock, times(1))
                .findClientByName(name);
    }

    @Test
    public void shouldReturnEmptyOptionalIfClientIsNotFoundByName(){
        //given
        when(clientRepositoryMock.findClientByName(name))
                .thenReturn(null);

        //when
        Optional<Client> actualEmptyOptional = clientService.getClientByName(name);
        Optional<Client> expectedEmptyOptional = Optional.empty();

        //then
        assertEquals(expectedEmptyOptional, actualEmptyOptional);
        verify(clientRepositoryMock, times(1))
                .findClientByName(name);
    }
}