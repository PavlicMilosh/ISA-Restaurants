package com.isa.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Q on 15-Apr-17.
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "guest")
public class Guest extends User
{

    @Column(name = "guest_enabled")
    private Boolean enabled;


    public Guest(String email, String password, String firstName, String lastName)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = false;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (!(o instanceof Guest))
            return false;

        User user = (Guest) o;

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

