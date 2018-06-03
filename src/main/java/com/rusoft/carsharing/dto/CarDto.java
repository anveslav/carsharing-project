package com.rusoft.carsharing.dto;

import com.rusoft.carsharing.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarDto {

    @NotNull
    private String model;

    @NotNull
    private String productionYear;

    private Client client;

}
