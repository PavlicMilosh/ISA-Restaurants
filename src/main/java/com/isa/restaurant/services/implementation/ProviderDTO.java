package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Milos on 22-Jun-17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class ProviderDTO
{
    private Long id;
    private Boolean enabled;
    private String authorities;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String oldPassword;

    public ProviderDTO()
    {
        this.authorities = Role.PROVIDER;
    }

    public ProviderDTO(String email, String password, String firstName, String lastName, String oldPassword)
    {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.oldPassword = oldPassword;
    }
}
