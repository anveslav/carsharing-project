package com.rusoft.carsharing.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "production_year")
    private String productionYear;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;

}
