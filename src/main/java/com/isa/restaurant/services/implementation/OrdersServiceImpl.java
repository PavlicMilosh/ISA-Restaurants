package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.DrinkDishDTO;
import com.isa.restaurant.domain.DTO.FinishedOrderDTO;
import com.isa.restaurant.domain.DTO.OrderItemDTO;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.OrderItemService;
import com.isa.restaurant.services.OrdersService;
import com.isa.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by djuro on 4/22/2017.
 */
@Service
public class OrdersServiceImpl implements OrdersService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private DishMarkRepository dishMarkRepository;

    @Autowired
    private DrinkMarkRepository drinkMarkRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Order getById(Long id)
    {
        Order order=null;
        try
        {
            order = orderRepository.findById(id);
        }
        catch(Exception e)
        {
        }
        return order;

    }

    @Override
    public Order getByUser(User user)
    {
        Order order=null;
        try
        {
            //order = orderRepository.findByUser(user);
        }
        catch (Exception e)
        {
        }
        return order;
    }

    @Override
    public Order addOrder(Order order, Long tableId)
    {
        Order saved = null;
        try
        {
            Set<OrderItem> savedOrderItems=new HashSet<OrderItem>();
            for (OrderItem orderItem:order.getOrderItems())
            {
                OrderItem savedOrderItem=orderItemService.addOrderItem(orderItem);
                savedOrderItems.add(savedOrderItem);
            }
            order.setOrderItems(savedOrderItems);
            RestaurantTable table= tableRepository.findById(tableId);
            order.setOrderTable(table);
            saved = orderRepository.save(order);
        }
        catch(Exception e)
        {
        }
        return saved;
    }

    @Override
    public Order finishOrder(Long id) {
        Order saved = null;
        try {
            saved = orderRepository.findById(id);
        }
        catch (Exception e)
        {
        }
        saved.setFinished(true);
        orderRepository.save(saved);
        Bill bill=null;
        try {
            bill=billRepository.findByUser(saved.getWaiter());
        }
        catch (Exception e)
        {
        }
        if(bill==null){
            bill=new Bill(saved.getWaiter());
        }
        bill.addOrder(saved);
        return saved;

    }

    @Override
    public Set<OrderItemDTO> getOrdersForDeliver(Long waiterId)
    {
//        User u = userRepository.findById(waiterId);
//        Waiter waiter = (Waiter) u;
//        RestaurantOrders restorantOrders=restaurantOrdersRepository.findByRestaurantId(waiter.getRestaurant().getId());
//        Set<OrderItemDTO> ordersForDeliverd=new HashSet<OrderItemDTO>();
//        Long waiterRegion=userService.getWaiterRegionId(waiterId);
//        for(Order o:restorantOrders.getOrders())
//        {
//            if(o.getFinished()==true && o.getDelivered()==false)
//            {
//                if(o.getOrderTable().getRegion().getId()==waiterRegion){
//                    ordersForDeliverd.add(new OrderItemDTO(o));
//                }
//            }
//        }
//        return ordersForDeliverd;

        return new HashSet<>();
    }

    @Override
    public Set<Long> getTablesForCreatingBill(Long waiterId)
    {
//        User u = userRepository.findById(waiterId);
//        Waiter waiter = (Waiter) u;
//        Restaurant restaurant=waiter.getRestaurant();
//        RestaurantOrders restorantOrders=restaurantOrdersRepository.findByRestaurantId(restaurant.getId());
//        Set<Long>  tablesForCreatingBills=new HashSet<Long>();
//        Long waiterRegion=userService.getWaiterRegionId(waiterId);
//        for(Order o:restorantOrders.getOrders())
//        {
//            if(o.getFinished()==true && o.getDelivered()==true && o.getDelivered()==false)
//            {
//                if(o.getOrderTable().getRegion().getId()==waiterRegion){
//                    tablesForCreatingBills.add(o.getId());
//                }
//            }
//        }
//        return tablesForCreatingBills;

        return new HashSet<>();
    }

    @Override
    public OrderItemDTO deliveredOrder(Long orderId)
    {
        Order order=orderRepository.findById(orderId);
        order.setDelivered(true);
        orderRepository.save(order);
        return (new OrderItemDTO(order));
    }

    @Override
    public Set<Long> getPreparingOrderId(Long userId)//++
    {
//        User u = userRepository.findById(userId);
//        Restaurant r = null;
//        if(u instanceof Bartender)
//        {
//            r = ((Bartender) u).getRestaurant();
//        }
//        else if(u instanceof Cook)
//        {
//            r = ((Cook) u).getRestaurant();
//        }
//        RestaurantOrders restaurantOrders=restaurantOrdersRepository.findByRestaurantId(r.getId());
//        Set<Long> ordersId=new HashSet<Long>();
//        for(Order o:restaurantOrders.getOrders())
//        {
//            if(!o.getFinished())
//            {
//                for(OrderItem oi:o.getOrderItems())
//                {
//                    if(oi.getFinished()==false && oi.getPreparing()==true)
//                    {
//                        if(oi.getUserId()==userId)
//                        {
//                            ordersId.add(o.getId());
//                        }
//                    }
//                }
//            }
//        }
//        return ordersId;

        return new HashSet<>();
    }

    @Override
    public Set<FinishedOrderDTO> getGuestOrders(Long guestId) {
        Set<FinishedOrderDTO> orders = new HashSet<FinishedOrderDTO>();
        List<Reservation> reservations = reservationRepository.getReservationsByReserverId(guestId);
        for (Reservation reservation : reservations) {
            if (reservation.getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
            {
                for (Order o : reservation.getOrders())
                {
                    if (o.getGuest().getId() == guestId)
                    {
                        if (o.getDelivered() && !o.getIsMarked()) orders.add(new FinishedOrderDTO(o,reservation.getRestaurant().getId(),
                                reservation.getRestaurant().getName()));
                    }
                }
            }
        }
        List<Invitation> invitations = invitationRepository.getUsersAcceptedInvitationsByUserId(guestId);
        for (Invitation invitation : invitations)
        {
            if (invitation.getReservation().getStatus().equalsIgnoreCase(ReservationStatus.FINISHED))
            {
                for (Order o : invitation.getReservation().getOrders())
                {
                    if (o.getGuest().getId() == guestId)
                    {
                        if (o.getDelivered() && !o.getIsMarked()) orders.add(new FinishedOrderDTO(o,invitation.getReservation().getRestaurant().getId(),
                                invitation.getReservation().getRestaurant().getName()));
                    }
                }
            }
        }

        return orders;
    }

    @Override
    public FinishedOrderDTO markOrder(Long guestId, Long orderId, Double mark)
    {
        User u = userRepository.findById(guestId);
        Guest guest = (Guest) u;
        Order order = orderRepository.findById(orderId);
        if(!order.getIsMarked())
        {
            order.setIsMarked(true);
            order.setMark(mark);
            orderRepository.save(order);
            for(OrderItem oi:order.getOrderItems())
            {
                if(oi.getIsDish())
                {
                    DishMark dishMark=null;
                    dishMark=dishMarkRepository.findByDishId(oi.getDish().getId());
                    if(dishMark!=null) dishMark.mark(mark);
                    else dishMark=new DishMark(mark,1,guest, oi.getDish());
                    dishMarkRepository.save(dishMark);
                }
                else
                {
                    DrinkMark drinkMark=null;
                    drinkMark=drinkMarkRepository.findByDrinkId(oi.getDrink().getId());
                    if(drinkMark!=null) drinkMark.mark(mark);
                    else drinkMark=new DrinkMark(mark,1,guest,oi.getDrink());
                    drinkMarkRepository.save(drinkMark);
                }
            }
        }
        return (new FinishedOrderDTO(order));
    }

    @Override
    public Set<DrinkDishDTO> getRestaurantDrinkDishMark(Long restaurantId)
    {
        Restaurant restaurant=restaurantRepository.findById(restaurantId);
        Set<DrinkDishDTO> items=new HashSet<DrinkDishDTO>();
        for(Drink drink:restaurant.getDrinks())
        {
            DrinkMark drinkMark=null;
            drinkMark=drinkMarkRepository.findByDrinkId(drink.getId());
            if(drinkMark!=null) items.add(new DrinkDishDTO(drink,drinkMark.getValue()));
            else items.add(new DrinkDishDTO(drink,0.0));
        }
        for(Dish dish:restaurant.getDishes())
        {
            DishMark dishMark=null;
            dishMark=dishMarkRepository.findByDishId(dish.getId());
            if(dishMark!=null) items.add(new DrinkDishDTO(dish,dishMark.getValue()));
            else items.add(new DrinkDishDTO(dish,0.0));
        }
        return items;
    }
}
