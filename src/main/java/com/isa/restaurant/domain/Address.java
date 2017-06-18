package com.isa.restaurant.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Table(name = "address")
public class Address
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "address_city")
    private String city;

    @Column(name = "address_street_and_number")
    private String address;

    @Column(name = "address_postal_code")
    private String postalCode;
}
