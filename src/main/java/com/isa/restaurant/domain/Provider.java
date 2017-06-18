package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Created by Milos on 17-May-17.
 */
@Entity
@Getter
@Setter
public class Provider extends User
{
    @JsonIgnore
    @OneToMany(mappedBy = "provider", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Offer> offers;

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
