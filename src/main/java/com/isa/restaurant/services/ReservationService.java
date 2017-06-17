package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.exceptions.ReservationException;
import com.isa.restaurant.exceptions.UserNotFoundException;
import com.isa.restaurant.exceptions.InvalidDateException;
import com.isa.restaurant.exceptions.RestaurantNotFoundException;

import java.util.List;

/**
 * Created by Q on 13-May-17.
 */
public interface ReservationService
{
    ReservationDTO addReservation(Long guestId, ReservationDTO reservation)
            throws UserNotFoundException, RestaurantNotFoundException, InvalidDateException, ReservationException;

    List<RestaurantTableDTO> getTables(Long guestId, ReservationDTO reservationDTO)
            throws UserNotFoundException, RestaurantNotFoundException, InvalidDateException, ReservationException;

    Integer getNumberOfVisitedRestaurants(Long guestId);

    List<ReservationWithOrdersDTO> getReservations(Long guestId)
            throws UserNotFoundException;

    List<InvitationDTO> getAcceptedInvitations(Long guestId)
            throws UserNotFoundException;

    ReservationDTO updateReservation(Long guestId, Long reservationId, ReservationUpdateDTO reservationUpdateData)
            throws ReservationException, UserNotFoundException;

    List<HistoryDTO> getHistoryOfVisits(Long guestId)
            throws UserNotFoundException;
}
