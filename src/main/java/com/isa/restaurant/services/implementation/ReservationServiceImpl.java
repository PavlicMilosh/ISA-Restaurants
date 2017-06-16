package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.exceptions.GuestNotFoundException;
import com.isa.restaurant.exceptions.InvalidDateException;
import com.isa.restaurant.exceptions.ReservingOccupiedTablesException;
import com.isa.restaurant.exceptions.RestaurantNotFoundException;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.MailService;
import com.isa.restaurant.services.ReservationService;
import com.isa.restaurant.ulitity.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private final InvitationRepository invitationRepository;
    private final DrinkRepository drinkRepository;
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserRepository userRepository,
                                  RestaurantRepository restaurantRepository,
                                  TableRepository tableRepository,
                                  InvitationRepository invitationRepository,
                                  DrinkRepository drinkRepository,
                                  DishRepository dishRepository,
                                  OrderRepository orderRepository,
                                  VerificationTokenRepository verificationTokenRepository,
                                  MailService mailService)
    {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.tableRepository = tableRepository;
        this.invitationRepository = invitationRepository;
        this.drinkRepository = drinkRepository;
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.mailService = mailService;
    }


    @Override
    @Transactional
    public ReservationDTO addReservation(Long guestId, ReservationDTO reservationDTO)
            throws GuestNotFoundException, RestaurantNotFoundException, InvalidDateException, ReservingOccupiedTablesException
    {
        Guest guest = (Guest) userRepository.findById(guestId);
        Restaurant restaurant = restaurantRepository.findByName(reservationDTO.getRestaurant().getName());

        Date dateTimeStart = Utilities.createDateFromString(reservationDTO.getStartDate(), reservationDTO.getStartTime());
        Date dateTimeEnd = Utilities.addMinutesToDate(dateTimeStart, reservationDTO.getDuration());

        if (guest == null)
            throw new GuestNotFoundException();
        if (restaurant == null)
            throw new RestaurantNotFoundException();
        if (dateTimeStart == null || dateTimeEnd == null)
            throw new InvalidDateException();
        if (dateTimeStart.before(new Date()))
            throw new InvalidDateException();
        if (hasOccupiedTables(reservationDTO.getTables(), restaurant, dateTimeStart, dateTimeEnd))
            throw new ReservingOccupiedTablesException();

        // STANDARD
        Reservation reservation = new Reservation(guest, restaurant, dateTimeStart, dateTimeEnd, ReservationStatus.SENT);

        // TABLES
        for (RestaurantTableDTO rtDTO : reservationDTO.getTables())
        {
            RestaurantTable table = tableRepository.findOne(rtDTO.getId());
            if (table.getLastReservationStart().after(dateTimeEnd))
                throw new ReservingOccupiedTablesException();
            table.setLastReservationStart(dateTimeStart);
            tableRepository.save(table);
            reservation.addTable(table);
        }

        // INVITATIONS
        for (GuestDTO i : reservationDTO.getInvites())
        {
            Guest invited = (Guest) userRepository.findById(i.getId());
            if (invited != null)
            {
                Invitation invitation = new Invitation(invited, reservation);
                invitationRepository.save(invitation);
                reservation.addInvitation(invitation);
                reservationRepository.save(reservation);
                VerificationToken verificationToken = new VerificationToken(invited, 0, VerificationTokenPurpose.INVITATION);
                verificationTokenRepository.save(verificationToken);
                mailService.sendInvitationEmail(reservation.getReserver(), invited, reservation, verificationToken.getToken());
            }
        }

        // ORDERS
        HashSet<OrderItem> orderItems = new HashSet<>();
        for (DrinkOrderDTO doDTO : reservationDTO.getDrinkOrders())
        {
            Drink drink = drinkRepository.getOne(doDTO.getId());
            orderItems.add(new OrderItem(drink, doDTO.getQuantity()));
        }
        for (DishOrderDTO doDTO : reservationDTO.getDishOrders())
        {
            Dish dish = dishRepository.getOne(doDTO.getId());
            orderItems.add(new OrderItem(dish, doDTO.getQuantity()));
        }
        if (!orderItems.isEmpty())
        {
            Order order = new Order(orderItems, dateTimeStart);
            order.setOrderTable(reservation.getTables().iterator().next());
            order.setGuest(reservation.getReserver());
            this.orderRepository.save(order);
            reservation.addOrder(order);
            reservationRepository.save(reservation);
        }

        reservationRepository.save(reservation);
        return new ReservationDTO(reservation);
    }


    public ReservationDTO updateReservation(Long guestId, Long reservationId)
    {
        return null;
    }


    @Override
    public List<RestaurantTableDTO> getTables(Long guestId, ReservationDTO reservationDTO)
            throws GuestNotFoundException, RestaurantNotFoundException, InvalidDateException, ReservingOccupiedTablesException
    {
        Guest guest = (Guest) userRepository.findById(guestId);
        Restaurant restaurant = restaurantRepository.findByName(reservationDTO.getRestaurant().getName());

        Date dateTimeStart = Utilities.createDateFromString(reservationDTO.getStartDate(), reservationDTO.getStartTime());
        Date dateTimeEnd = Utilities.addMinutesToDate(dateTimeStart, reservationDTO.getDuration());

        if (guest == null)
            throw new GuestNotFoundException();
        if (restaurant == null)
            throw new RestaurantNotFoundException();
        if (dateTimeStart == null || dateTimeEnd == null)
            throw new InvalidDateException();
        if (hasOccupiedTables(reservationDTO.getTables(), restaurant, dateTimeStart, dateTimeEnd))
            throw new ReservingOccupiedTablesException();

        HashMap<Boolean, List<RestaurantTable>> tables =  getTables(restaurant, dateTimeStart, dateTimeEnd);
        List<RestaurantTableDTO> ret = new ArrayList<>();

        for (RestaurantTable rt : tables.get(true))
            ret.add(new RestaurantTableDTO(rt, true));
        for (RestaurantTable rt : tables.get(false))
            ret.add(new RestaurantTableDTO(rt, false));

        return ret;
    }


    @Override
    public Integer getNumberOfVisitedRestaurants(Long guestId)
    {
        return 0;
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
                if (r.hasTable(rt.getId()) && !r.getStatus().equalsIgnoreCase(ReservationStatus.CANCELED))
                {
                    occupied.add(rt);
                    addedTable = true;
                    break;
                }
            }
            if (!addedTable) free.add(rt);
        }
        ret.put(true, occupied);
        ret.put(false, free);
        return ret;
    }



}
