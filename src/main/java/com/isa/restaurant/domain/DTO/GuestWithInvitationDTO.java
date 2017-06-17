package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Guest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Q on 17-Jun-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class GuestWithInvitationDTO
{
    Long id;
    String firstName;
    String lastName;
    String email;
    Boolean enabled;
    String status;

    public GuestWithInvitationDTO(Guest guest, String status)
    {
        this.id = guest.getId();
        this.email = guest.getEmail();
        this.firstName = guest.getFirstName();
        this.lastName = guest.getLastName();
        this.email = guest.getEmail();
        this.enabled = guest.getEnabled();
        this.status = status;
    }
}
