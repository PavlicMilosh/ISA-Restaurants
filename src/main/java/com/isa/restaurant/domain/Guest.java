package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.restaurant.domain.DTO.GuestDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Q on 15-Apr-17.
 */
@Getter
@Setter
@Entity
@Table(name = "guest")
@Indexed
public class Guest extends User
{

    public Guest()
    {
        this.enabled = true;
        this.authorities = Role.GUEST;
    }


    public Guest(String email, String password, String firstName, String lastName)
    {
        this();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public Guest(GuestDTO guestDTO)
    {
        super();
        this.id = guestDTO.getId();
        this.email = guestDTO.getEmail();
        this.firstName = guestDTO.getFirstName();
        this.lastName = guestDTO.getLastName();
        this.enabled = guestDTO.getEnabled();
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Guest)) return false;

        User user = (Guest) o;

        if (id != null ? !id.equals(user.id) : false) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;

        return true;
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

