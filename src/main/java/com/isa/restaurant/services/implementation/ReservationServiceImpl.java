package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.exceptions.UserNotFoundException;
import com.isa.restaurant.exceptions.InvalidDateException;
import com.isa.restaurant.exceptions.ReservationException;
import com.isa.restaurant.exceptions.RestaurantNotFoundException;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.FriendshipService;
import com.isa.restaurant.services.MailService;
import com.isa.restaurant.services.ReservationService;
import com.isa.restaurant.ulitity.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
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
    private final FriendshipRepository friendshipRepository;
    private final RestaurantRepository restaurantRepository;
    private final TableRepository tableRepository;
    private final RegionRepository regionRepository;
    private final InvitationRepository invitationRepository;
    private final DrinkRepository drinkRepository;
    private final DishRepository dishRepository;
    private final OrderRepository orderRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final OrderItemRepository orderItemRepository;
    private final MailService mailService;
    private final FriendshipService friendshipService;

    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserRepository userRepository,
                                  FriendshipRepository friendshipRepository,
                                  RestaurantRepository restaurantRepository,
                                  TableRepository tableRepository,
                                  RegionRepository regionRepository,
                                  InvitationRepository invitationRepository,
                                  DrinkRepository drinkRepository,
                                  DishRepository dishRepository,
                                  OrderRepository orderRepository,
                                  VerificationTokenRepository verificationTokenRepository,
                                  OrderItemRepository orderItemRepository,
                                  MailService mailService,
                                  FriendshipService friendshipService)
    {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.friendshipRepository = friendshipRepository;
        this.restaurantRepository = restaurantRepository;
        this.tableRepository = tableRepository;
        this.regionRepository = regionRepository;
        this.invitationRepository = invitationRepository;
        this.drinkRepository = drinkRepository;
        this.dishRepository = dishRepository;
        this.orderRepository = orderRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.orderItemRepository = orderItemRepository;
        this.mailService = mailService;
        this.friendshipService = friendshipService;
    }


    @Override
    @Transactional
    public ReservationDTO addReservation(Long guestId, ReservationDTO reservationDTO)
            throws UserNotFoundException, RestaurantNotFoundException, InvalidDateException, ReservationException
    {
        Guest guest = (Guest) userRepository.findById(guestId);
        Restaurant restaurant = restaurantRepository.findByName(reservationDTO.getRestaurant().getName());

        Date dateTimeStart = Utilities.createDateFromString(reservationDTO.getStartDate(), reservationDTO.getStartTime());
        Date dateTimeEnd = Utilities.addMinutesToDate(dateTimeStart, reservationDTO.getDuration());

        if (guest == null)
            throw new UserNotFoundException();
        if (restaurant == null)
            throw new RestaurantNotFoundException();
        if (dateTimeStart == null || dateTimeEnd == null)
            throw new InvalidDateException();
        if (dateTimeStart.before(new Date()))
            throw new InvalidDateException();
        if (hasOccupiedTables(reservationDTO.getTables(), restaurant, dateTimeStart, dateTimeEnd))
            throw new ReservationException();
        if (reservationDTO.getTables().isEmpty())
            throw new ReservationException();

        // STANDARD
        Reservation reservation = new Reservation(guest, restaurant, dateTimeStart, dateTimeEnd, ReservationStatus.SENT);

        // TABLES

        for (RestaurantTableDTO rtDTO : reservationDTO.getTables())
        {
            RestaurantTable attempt = tableRepository.findOne(rtDTO.getId());
            RestaurantTable restaurantTable = new RestaurantTable(rtDTO);
            restaurantTable.setRestaurant(attempt.getRestaurant());
            restaurantTable.setRegion(attempt.getRegion());
            restaurantTable.setBills(attempt.getBills());
            try
            {
                restaurantTable = tableRepository.save(restaurantTable);
                reservation.addTable(restaurantTable);
            }
            catch(OptimisticLockingFailureException e)
            {
                List<Reservation> reservations = reservationRepository.getReservationsByRestaurantAndDate(restaurant.getId(), dateTimeStart, dateTimeEnd);

                for(Reservation res : reservations)
                {
                    if(res.getTables().contains(restaurantTable))
                        throw new ReservationException();
                    else
                    {
                        for(RestaurantTable rt : res.getTables())
                        {
                            if(rt.getId() == restaurantTable.getId())
                            {
                                restaurantTable.setVersion(rt.getVersion());
                                restaurantTable = tableRepository.save(restaurantTable);
                                reservation.addTable(restaurantTable);
                                break;
                            }
                        }

                    }
                }
            }
        }


        reservation = reservationRepository.save(reservation);

        // INVITATIONS
        for (GuestDTO i : reservationDTO.getInvites())
        {
            Guest invited = (Guest) userRepository.findById(i.getId());
            Friendship friendship = friendshipRepository.findByBothUsers(i.getId(), guest.getId());

            if (invited != null && friendship != null)
            {
                Invitation invitation = new Invitation(invited, reservation);
                invitation = invitationRepository.save(invitation);
                reservation.addInvitation(invitation);
                VerificationToken verificationToken = new VerificationToken(invited, 0, VerificationTokenPurpose.INVITATION);
                verificationToken = verificationTokenRepository.save(verificationToken);
                mailService.sendInvitationEmail(reservation.getReserver(), invited, invitation, verificationToken.getToken());
            }
        }

        // ORDERS
        HashSet<OrderItem> orderItems = new HashSet<>();
        for (DrinkOrderDTO doDTO : reservationDTO.getDrinkOrders())
        {
            Drink drink = drinkRepository.getOne(doDTO.getId());
            OrderItem orderItem = new OrderItem(drink, doDTO.getQuantity());
            orderItem = orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        for (DishOrderDTO doDTO : reservationDTO.getDishOrders())
        {
            Dish dish = dishRepository.getOne(doDTO.getId());
            OrderItem orderItem = new OrderItem(dish, doDTO.getQuantity());
            orderItem = orderItemRepository.save(orderItem);
            orderItems.add(orderItem);
        }
        Order savedOrder=null;
        if (!orderItems.isEmpty())
        {
            Order order = new Order(orderItems, dateTimeStart);
            order.setOrderTable(reservation.getTables().iterator().next());
            order.setGuest(reservation.getReserver());
            order.setReservationId(reservation.getId());
            order = orderRepository.save(order);
            reservation.addOrder(order);
            restaurant.addOrder(order);
        }

        ReservationDTO ret = new ReservationDTO(reservation);
        return ret;
    }


    @Override
    @Transactional
    public List<RestaurantTableDTO> getTables(Long guestId, ReservationDTO reservationDTO)
            throws UserNotFoundException, RestaurantNotFoundException, InvalidDateException, ReservationException
    {
        Guest guest = (Guest) userRepository.findById(guestId);
        Restaurant restaurant = restaurantRepository.findByName(reservationDTO.getRestaurant().getName());

        Date dateTimeStart = Utilities.createDateFromString(reservationDTO.getStartDate(), reservationDTO.getStartTime());
        Date dateTimeEnd = Utilities.addMinutesToDate(dateTimeStart, reservationDTO.getDuration());

        if (guest == null)
            throw new UserNotFoundException();
        if (restaurant == null)
            throw new RestaurantNotFoundException();
        if (dateTimeStart == null || dateTimeEnd == null)
            throw new InvalidDateException();
        if (hasOccupiedTables(reservationDTO.getTables(), restaurant, dateTimeStart, dateTimeEnd))
            throw new ReservationException();

        HashMap<Boolean, List<RestaurantTable>> tables =  getTables(restaurant, dateTimeStart, dateTimeEnd);
        List<RestaurantTableDTO> ret = new ArrayList<>();

        for (RestaurantTable rt : tables.get(true))
            ret.add(new RestaurantTableDTO(rt, true));
        for (RestaurantTable rt : tables.get(false))
            ret.add(new RestaurantTableDTO(rt, false));

        return ret;
    }


    @Override
    @Transactional
    public Integer getNumberOfVisitedRestaurants(Long guestId)
    {
        Map<Long, Integer> visits = new HashMap<>();
        Integer ret = 0;

        List<Reservation> reservations = reservationRepository.getReservationsByReserverId(guestId);
        List<Invitation> invitations = invitationRepository.getUsersAcceptedInvitationsByUserId(guestId);

        for (Reservation reservation : reservations)
        {
            if (reservation.getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
            {
                Long restaurantId = reservation.getRestaurant().getId();
                if (visits.containsKey(restaurantId))
                    visits.put(restaurantId, visits.get(restaurantId) + 1);
                else
                    visits.put(restaurantId, 1);
            }
        }
        for (Invitation invitation : invitations)
        {
            if (invitation.getReservation().getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
            {
                Long restaurantId = invitation.getReservation().getRestaurant().getId();
                if (visits.containsKey(restaurantId))
                    visits.put(restaurantId, visits.get(restaurantId) + 1);
                else
                    visits.put(restaurantId, 1);
            }
        }

        for (Integer numOfVisits : visits.values())
            ret += numOfVisits;

        return  ret;
    }


    @Override
    @Transactional
    public List<ReservationWithOrdersDTO> getReservations(Long guestId)
            throws UserNotFoundException
    {
        if (userRepository.findById(guestId) == null)
            throw new UserNotFoundException("");

        List<Reservation> reservations = reservationRepository.getReservationsByReserverId(guestId);
        List<ReservationWithOrdersDTO> ret = new ArrayList<>();

        for (Reservation reservation : reservations)
            if (!reservation.getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
                ret.add(new ReservationWithOrdersDTO(reservation));

        return ret;
    }


    @Override
    @Transactional
    public List<InvitationDTO> getAcceptedInvitations(Long guestId)
            throws UserNotFoundException
    {
        if (userRepository.findById(guestId) == null)
            throw new UserNotFoundException();

        List<Invitation> invitations = invitationRepository.getUsersAcceptedInvitationsByUserId(guestId);
        List<InvitationDTO> ret = new ArrayList<>();

        for (Invitation invitation : invitations)
            if (invitation.getReservation().getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
                ret.add(new InvitationDTO(invitation));

        return ret;
    }


    @Override
    @Transactional
    public ReservationDTO updateReservation(Long guestId, Long reservationId, ReservationUpdateDTO reservationUpdateData)
            throws ReservationException, UserNotFoundException
    {
        Guest attendant = (Guest) userRepository.findById(guestId);
        Reservation reservation = reservationRepository.findById(reservationId);

        if (attendant == null)
            throw new UserNotFoundException();
        if (reservation == null)
            throw new ReservationException();
        if (reservation.getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
            throw new ReservationException();

        for (Order o : reservation.getOrders())
            if (o.getId() == reservationUpdateData.getOrderId())
                reservation.deleteOrder(o.getId());

        HashSet<OrderItem> newItems = new HashSet<>();
        for (DrinkOrderDTO d : reservationUpdateData.getDrinkOrders())
        {
            Drink drink = drinkRepository.findById(d.getId());
            OrderItem orderItem = new OrderItem(drink, d.getQuantity());
            orderItem = orderItemRepository.save(orderItem);
            newItems.add(orderItem);
        }
        for (DishOrderDTO d : reservationUpdateData.getDishOrders())
        {
            Dish dish = dishRepository.findById(d.getId());
            OrderItem orderItem = new OrderItem(dish, d.getQuantity());
            orderItem = orderItemRepository.save(orderItem);
            newItems.add(orderItem);
        }

        if (!newItems.isEmpty())
        {
            Order order = new Order(newItems, reservation.getDateTimeStart());
            order.setGuest(attendant);
            reservation.addOrder(order);
            order = orderRepository.save(order);
            Restaurant restaurant = reservation.getRestaurant();
            restaurant.addOrder(order);
            restaurantRepository.save(restaurant);
        }

        reservation = reservationRepository.save(reservation);

        return new ReservationDTO(reservation);
    }


    @Override
    @Transactional
    public List<HistoryDTO> getHistoryOfVisits(Long guestId)
            throws UserNotFoundException
    {
        if (userRepository.findById(guestId) == null)
            throw new UserNotFoundException();

        List<Reservation> reservations = reservationRepository.getReservationsByReserverId(guestId);
        List<Invitation> invitations = invitationRepository.getUsersAcceptedInvitationsByUserId(guestId);
        List<HistoryDTO> ret = new ArrayList<>();

        Set<GuestDTO> friends = friendshipService.getFriends(guestId);
        Set<Long> friendIds = new HashSet<>();
        for (GuestDTO friend : friends)
            friendIds.add(friend.getId());

        for (Reservation reservation : reservations)
            if (reservation.getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
                ret.add(generateHistoryData(reservation, guestId, friendIds));

        for (Invitation invitation : invitations)
            if (invitation.getReservation().getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
                ret.add(generateHistoryData(invitation.getReservation(), guestId, friendIds));

        return ret;
    }


    @Override
    @Transactional
    public void deleteReservation(Long guestId, Long reservationId)
            throws ReservationException, UserNotFoundException
    {
        Reservation reservation = reservationRepository.findById(reservationId);

        if (userRepository.findById(guestId) == null)
            throw new UserNotFoundException();
        if (reservation == null)
            throw new ReservationException();
        if (reservation.getReserver().getId() != guestId)
            throw new ReservationException();

        reservationRepository.delete(reservationId);
    }


    private HistoryDTO generateHistoryData(Reservation reservation, Long guestId, Set<Long> friendIds)
    {
        Double restaurantFriendsMark = 0.0;
        Double restaurantMeanMark = 0.0;
        Double restaurantMyMark = 0.0;
        Long orderId=null;
        Double mealMyMark=0.0;
        Boolean isMark=false;
        Double waiterMark=0.0;
        String waiterFirstName=null;
        String waiterLastName=null;
        Long waiterId=null;
        Waiter waiter=null;

        for (RestaurantMark rm : reservation.getRestaurant().getRestaurantMarks()) {
            if (friendIds.contains(rm.getGuest().getId()))
                restaurantFriendsMark += rm.getValue();
            if (rm.getGuest().getId() == guestId) {
                restaurantMyMark = rm.getValue();
            }
            restaurantMeanMark += rm.getValue();
        }

        if (restaurantFriendsMark > 0)
            restaurantFriendsMark /= friendIds.size();
        else
            restaurantFriendsMark = null;

        if (restaurantMeanMark > 0)
            restaurantMeanMark /= reservation.getRestaurant().getRestaurantMarks().size();
        else
            restaurantMeanMark = null;


        for (Order order : reservation.getOrders())
        {
            if (order.getGuest().getId() == guestId)
            {
                orderId = order.getId();
                mealMyMark = order.getMark();
                isMark = order.getIsMarked();
                waiter = order.getWaiter();
                if(isMark) {
                    mealMyMark=0.0;
                    for (OrderItem oi : order.getOrderItems()) {
                        if (oi.getIsDish()) {
                            for (DishMark dm : oi.getDish().getDishMarks()) {
                                if(dm.getGuest().getId()==guestId){
                                    mealMyMark += dm.getValue();
                                    break;
                                }
                            }
                        }
                        else
                        {
                            for (DrinkMark dm : oi.getDrink().getDrinkMarks()) {
                                if(dm.getGuest().getId()==guestId){
                                    mealMyMark += dm.getValue();
                                    break;
                                }
                            }
                        }
                    }
                    mealMyMark = mealMyMark/order.getOrderItems().size();
                }
                break;
            }
        }
        if(waiter!=null)
        {
            waiterId=waiter.getId();
            waiterFirstName=waiter.getFirstName();
            waiterLastName=waiter.getLastName();
            for(WaiterMark wm : waiter.getWaiterMarks())
            {
                if(wm.getGuest().getId() == guestId)
                {
                    waiterMark=wm.getValue();
                    break;
                }
            }
        }
        HistoryDTO history = new HistoryDTO(reservation,
                restaurantFriendsMark, restaurantMyMark,
                restaurantMeanMark, orderId, mealMyMark,
                isMark, waiterMark, waiterFirstName,
                waiterLastName, waiterId);

        return history;
    }



    private void clearOrder(Long orderId)
    {
        Order order = orderRepository.findById(orderId);
        orderRepository.delete(orderId);
        for (OrderItem orderItem : order.getOrderItems())
            orderItemRepository.delete(orderItem.getId());
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
