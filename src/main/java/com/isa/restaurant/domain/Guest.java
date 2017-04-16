package com.isa.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Guest(String username, String password, String firstName, String lastName)
    {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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
        if (username != null ? !username.equals(user.username) : user.username != null)
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
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        return result;
    }

}

