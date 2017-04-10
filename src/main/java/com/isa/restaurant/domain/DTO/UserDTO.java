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
@AllArgsConstructor
@Getter
@Setter
public class UserDTO
{
    String username;

    String firstName;

    String lastName;

    String userType;

    public UserDTO(User user)
    {
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        //add userType
    }
}
