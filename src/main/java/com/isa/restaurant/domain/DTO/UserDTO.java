package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.SystemManager;
import com.isa.restaurant.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Milos on 10-Apr-17.
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class UserDTO
{
    String firstName;
    String lastName;
    String email;
    String authorities;
    String password;
    String oldPassword;
    Long id;

    public UserDTO(User user)
    {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.id = user.getId();
        this.password = user.getPassword();
        this.authorities = user.getAuthorities();
    }
}
