package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.ReservationDTO;
import com.isa.restaurant.domain.Reservation;

/**
 * Created by Q on 13-May-17.
 */
public interface ReservationService
{
    ReservationDTO addReservation(Long guestId, ReservationDTO reservation);

}
