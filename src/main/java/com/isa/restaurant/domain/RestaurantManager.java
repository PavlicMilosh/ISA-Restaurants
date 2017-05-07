package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restaurant_manager")
public class RestaurantManager extends User
{
    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    public RestaurantManager(String email, String pass, String firstName, String lastName, Restaurant restaurant)
    {
        this.email = email;
        this.password = pass;
        this.firstName = firstName;
        this.lastName = lastName;
        this.restaurant = restaurant;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof RestaurantManager))
            return false;

        RestaurantManager user = (RestaurantManager) o;

        if (id != null ? !id.equals(user.id) : false)
            return false;
        if (email != null ? !email.equals(user.email) : user.email != null)
            return false;
        if (password != null ? !password.equals(user.password) : user.password != null)
            return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null)
            return false;

        return lastName != null ? lastName.equals(user.lastName) : user.lastName == null;
    }

    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }
}
