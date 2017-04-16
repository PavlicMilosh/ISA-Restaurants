package com.isa.restaurant.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Milos on 10-Apr-17.
 */
@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id", unique = true, nullable = false)
    protected Long id;

    @Column(name = "user_username", unique = true, nullable = false)
    protected String username;

    @Column(name = "user_password")
    protected String password;

    @Column(name = "user_first_name")
    protected String firstName;

    @Column(name = "user_last_name")
    protected String lastName;

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != null && !id.equals(user.id)) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
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
