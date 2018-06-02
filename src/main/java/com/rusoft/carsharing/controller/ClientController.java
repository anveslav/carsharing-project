package com.rusoft.carsharing.controller;


import com.rusoft.carsharing.dto.ClientDto;
import com.rusoft.carsharing.model.Client;
import com.rusoft.carsharing.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public ClientDto addClient(@RequestParam("client_name") String clientName,
                               @RequestParam("birth_year") String birthYear,
                               @RequestParam("car_model") String carModel,
                               @RequestParam("car_year") String carYear) {

        return clientService.addClient(clientName, birthYear, carModel, carYear);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void deleteClient(@RequestParam("client_name") String clientName,
                             @RequestParam("car_model") String carModel) {
        clientService.deleteClient(clientName, carModel);
    }
}
