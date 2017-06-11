package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.MailService;
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
    private final InvitationRepository invitationRepository;
    private final DrinkRepository drinkRepository;
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
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
        this.mailService = mailService;
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
        reservation.setStatus(ReservationStatus.SENT);

        // TABLES
        for (RestaurantTableDTO rtDTO : reservationDTO.getTables())
        {
            RestaurantTable table = tableRepository.findOne(rtDTO.getId());
            if (rtDTO.getVersion() >= table.getVersion())
            {
                table.incrementVersion();
                tableRepository.save(table);
                reservation.addTable(table);
            }
            else
            {
                return null;
            }
        }

        // INVITATIONS
        Set<Invitation> invitations = new HashSet<>();
        for (GuestDTO i : reservationDTO.getInvites())
        {
            Guest invited = (Guest) userRepository.findById(i.getId());

            if (invited != null)
            {
                Invitation invitation = new Invitation(invited, reservation);
                invitationRepository.save(invitation);
                reservation.addInvitation(invitation);
                reservationRepository.save(reservation);
                VerificationToken verificationToken = new VerificationToken(guest, null, VerificationTokenPurpose.INVITATION);
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
            Drink drink = drinkRepository.getOne(doDTO.getId());
            orderItems.add(new OrderItem(drink, doDTO.getQuantity()));
        }
        if (!orderItems.isEmpty())
        {
            Order order = new Order(orderItems, reservationDateTimeStart);
            order.setOrderTable(reservation.getTables().iterator().next());
            this.orderRepository.save(order);
            reservation.setOrder(order);
            reservationRepository.save(reservation);
        }

        reservationRepository.save(reservation);
        return new ReservationDTO(reservation);
    }


    @Override
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
                if (r.hasTable(rt.getId()) &&
                        !r.getStatus().equalsIgnoreCase(ReservationStatus.CANCELED))
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
