package com.rusoft.carsharing.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth_year")
    private String birthYear;

    @OneToOne(mappedBy = "client")
    private Car car;

}
