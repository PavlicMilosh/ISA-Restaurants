package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Q on 16-May-17.
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Setter
@Getter
public class InvitationDTO
{
    private Long id;
    private ReservationDTO reservationDTO;
    private GuestDTO invited;
    private String invitationStatus;
}
