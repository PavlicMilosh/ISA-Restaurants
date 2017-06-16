package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.ReservationDTO;
import com.isa.restaurant.domain.DTO.RestaurantTableDTO;
import com.isa.restaurant.domain.Reservation;
import com.isa.restaurant.domain.RestaurantTable;
import com.isa.restaurant.exceptions.GuestNotFoundException;
import com.isa.restaurant.exceptions.InvalidDateException;
import com.isa.restaurant.exceptions.ReservingOccupiedTablesException;
import com.isa.restaurant.exceptions.RestaurantNotFoundException;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Q on 13-May-17.
 */
public interface ReservationService
{
    ReservationDTO addReservation(Long guestId, ReservationDTO reservation)
            throws GuestNotFoundException, RestaurantNotFoundException, InvalidDateException, ReservingOccupiedTablesException;
    List<RestaurantTableDTO> getTables(Long guestId, ReservationDTO reservationDTO)
            throws GuestNotFoundException, RestaurantNotFoundException, InvalidDateException, ReservingOccupiedTablesException;
    Integer getNumberOfVisitedRestaurants(Long guestId);
}
