package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Milos on 12-Apr-17.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restaurant_manager")
public class RestaurantManager extends User
{
    @ManyToOne
    private Restaurant restaurant;

    public RestaurantManager(String email, String pass, String firstName, String lastName, Restaurant restaurant)
    {
        this.email = email;
        this.password = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.restaurant = restaurant;
    }
}
