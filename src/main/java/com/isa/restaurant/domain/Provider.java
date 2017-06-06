package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * Created by Milos on 17-May-17.
 */
@Entity
@Getter
@Setter
public class Provider extends User
{
    public Provider()
    {
        this.enabled = true;
        this.authorities = Role.PROVIDER;
    }

    public Provider(String email, String password, String firstName, String lastName)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
