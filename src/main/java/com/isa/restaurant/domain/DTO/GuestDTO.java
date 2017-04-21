package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Guest;
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
public class GuestDTO
{
    String firstName;
    String lastName;
    String email;
    String enabled;

    public GuestDTO(Guest guest)
    {
        this.email = guest.getEmail();
        this.firstName = guest.getFirstName();
        this.lastName = guest.getLastName();
        this.email = guest.getEmail();
    }
}
