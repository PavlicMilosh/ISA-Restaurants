package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Milos on 30-May-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class UpdatingUser
{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UpdatingUser(User other)
    {
        this.id = other.getId();
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
        this.email = other.getEmail();
        this.password = other.getPassword();
    }
}
