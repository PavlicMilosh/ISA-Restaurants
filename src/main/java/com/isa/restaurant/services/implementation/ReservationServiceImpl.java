package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.ReservationDTO;
import com.isa.restaurant.domain.DTO.RestaurantTableDTO;
import com.isa.restaurant.repositories.ReservationRepository;
import com.isa.restaurant.repositories.RestaurantRepository;
import com.isa.restaurant.repositories.TableRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.ReservationService;
import com.isa.restaurant.ulitity.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Q on 13-May-17.
 */

@Service
public class ReservationServiceImpl implements ReservationService
{
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserRepository userRepository,
                                  RestaurantRepository restaurantRepository,
                                  TableRepository tableRepository)
    {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.tableRepository = tableRepository;
    }


    @Override
    @Transactional
    public ReservationDTO addReservation(Long guestId, ReservationDTO reservationDTO)
    {
        Guest guest = (Guest) userRepository.findById(guestId);
        Restaurant restaurant = restaurantRepository.findByName(reservationDTO.getRestaurant().getName());

        Date reservationDateTimeStart = Utilities.createDateFromString(reservationDTO.getStartDate(), reservationDTO.getStartTime());
        Date reservationDateTimeEnd = Utilities.addMinutesToDate(reservationDateTimeStart, reservationDTO.getDuration());

        if (guest == null ||
            restaurant == null ||
            reservationDateTimeStart == null ||
            reservationDateTimeEnd == null ||
            hasOccupiedTables(reservationDTO.getTables(), restaurant, reservationDateTimeStart, reservationDateTimeEnd)) return null;

        // STANDARD
        Reservation reservation = new Reservation();
        reservation.setReserver(guest);
        reservation.setRestaurant(restaurant);
        reservation.setDateTimeStart(reservationDateTimeStart);
        reservation.setDateTimeEnd(reservationDateTimeEnd);

        // TABLES
        for (RestaurantTableDTO rtDTO : reservationDTO.getTables())
        {
            RestaurantTable table = tableRepository.findOne(rtDTO.getId());
            reservation.addTable(table);
        }

        // INVITATIONS
        Set<Invitation> invitations = new HashSet<>();
        /* TODO 1: Implement adding invitations */
        reservation.setInvitations(invitations);

        reservationRepository.save(reservation);
        return new ReservationDTO(reservation);
    }


    private boolean hasOccupiedTables(Set<RestaurantTableDTO> reservedTables, Restaurant restaurant, Date reservationDateTimeStart, Date reservationDateTimeEnd)
    {
        boolean occupied = true;
        HashMap<Boolean, List<RestaurantTable>> relevantTables = getTables(restaurant, reservationDateTimeStart, reservationDateTimeEnd);

        for (RestaurantTable rt : relevantTables.get(occupied))
            for (RestaurantTableDTO rtDTO : reservedTables)
                if (rt.getId().longValue() == rtDTO.getId().longValue())
                    return true;

        return false;
    }


    public List<RestaurantTableDTO> getTables(Long guestId, ReservationDTO reservationDTO)
    {
        Guest guest = (Guest) userRepository.findById(guestId);
        Restaurant restaurant = restaurantRepository.findByName(reservationDTO.getRestaurant().getName());

        Date reservationDateTimeStart = Utilities.createDateFromString(reservationDTO.getStartDate(), reservationDTO.getStartTime());
        Date reservationDateTimeEnd = Utilities.addMinutesToDate(reservationDateTimeStart, reservationDTO.getDuration());

        if (guest == null ||
                restaurant == null ||
                reservationDateTimeStart == null ||
                reservationDateTimeEnd == null ||
                hasOccupiedTables(reservationDTO.getTables(), restaurant, reservationDateTimeStart, reservationDateTimeEnd)) return null;

        HashMap<Boolean, List<RestaurantTable>> tables =  getTables(restaurant, reservationDateTimeStart, reservationDateTimeEnd);

        List<RestaurantTableDTO> ret = new ArrayList<>();

        for (RestaurantTable rt : tables.get(true))
            ret.add(new RestaurantTableDTO(rt, true));
        for (RestaurantTable rt : tables.get(false))
            ret.add(new RestaurantTableDTO(rt, false));

        return ret;


    }


    private HashMap<Boolean, List<RestaurantTable>> getTables(Restaurant restaurant, Date reservationStart, Date reservationEnd)
    {
        // true - OCCUPIED, false - FREE

        List<Reservation> reservations = reservationRepository.getReservationsByRestaurantAndDate(restaurant.getId(), reservationStart, reservationEnd);
        List<RestaurantTable> occupied = new ArrayList<>();
        List<RestaurantTable> free = new ArrayList<>();
        HashMap<Boolean, List<RestaurantTable>> ret = new HashMap<>();

        boolean addedTable;

        for (RestaurantTable rt : restaurant.getTables())
        {
            addedTable = false;

            for (Reservation r : reservations)
            {
                if (r.hasTable(rt.getId()))
                {
                    occupied.add(rt);
                    addedTable = true;
                    break;
                }
            }

            if (!addedTable)
                free.add(rt);
        }

        ret.put(true, occupied);
        ret.put(false, free);

        return ret;
    }



}
