package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.OrderItemService;
import com.isa.restaurant.services.OrdersService;
import com.isa.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by djuro on 4/22/2017.
 */
@Service
@Transactional
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

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private RestaurantMarkRepository restaurantMarkRepository;

    @Autowired
    private WaiterMarkRepository waiterMarkRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

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
    public OrderItemDTO addOrder(OrderItemDTO orderDTO, Long id, Long waiterId, Long tableId)
    {
        Waiter w=(Waiter) userRepository.findById(waiterId);
        Restaurant restaurant=w.getRestaurant();
        Set<OrderItem> savedOrderItems=new HashSet<OrderItem>();
        for (OrderItmDTO orderItem:orderDTO.getOrderItems())
        {
            OrderItem savedOrderItem=null;
            Drink drink=null;
            Dish dish=null;
            if(orderItem.getIsDish())
            {
                dish=dishRepository.findById(orderItem.getDish().getId());
            }else
            {
                drink=drinkRepository.findById(orderItem.getDrink().getId());
            }
            if(orderItem.getIsDish()){
                savedOrderItem=new OrderItem(dish,orderItem.getNumber());
            }else
            {
                savedOrderItem=new OrderItem(drink,orderItem.getNumber());
            }
            OrderItem saved1=orderItemRepository.save(savedOrderItem);
            savedOrderItems.add(saved1);
        }
        Order order=new Order(savedOrderItems,orderDTO.getOrderTime());
        RestaurantTable table= tableRepository.findById(tableId);
        order.setOrderTable(table);
        order.setWaiter(w);
        Order saved = orderRepository.save(order);
        restaurant.addOrder(saved);
        System.out.println(restaurant.getId());
        restaurantRepository.save(restaurant);
        return new OrderItemDTO(saved);
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
        User u = userRepository.findById(waiterId);
        Waiter waiter = (Waiter) u;
        Set<OrderItemDTO> ordersForDeliverd=new HashSet<OrderItemDTO>();
        Long waiterRegion=userService.getWaiterRegionId(waiterId);
        for(Order o:waiter.getRestaurant().getOrders())
        {
            if(o.getFinished()==true && o.getDelivered()==false)
            {
                if(o.getOrderTable().getRegion().getId()==waiterRegion){
                    ordersForDeliverd.add(new OrderItemDTO(o));
                }
            }
        }
        return ordersForDeliverd;
    }

    @Override
    public Set<Long> getTablesForCreatingBill(Long waiterId)
    {
        User u = userRepository.findById(waiterId);
        Waiter waiter = (Waiter) u;
        Restaurant restaurant=waiter.getRestaurant();
        Set<Long>  tablesForCreatingBills=new HashSet<Long>();
        Long waiterRegion=userService.getWaiterRegionId(waiterId);
        for(Order o:restaurant.getOrders())
        {
            if(o.getFinished()==true && o.getDelivered()==true && o.getDelivered()==false)
            {
                if(o.getOrderTable().getRegion().getId()==waiterRegion){
                    tablesForCreatingBills.add(o.getId());
                }
            }
        }
        return tablesForCreatingBills;
    }

    @Override
    public OrderItemDTO deliveredOrder(Long orderId, Long userId)
    {
        Order order=orderRepository.findById(orderId);
        Waiter waiter = (Waiter) userRepository.findById(userId);
        order.setDelivered(true);
        order.setWaiter(waiter);
        orderRepository.save(order);
        return (new OrderItemDTO(order));
    }

    @Override
    public Set<Long> getPreparingOrderId(Long userId)
    {
        User u = userRepository.findById(userId);
        Restaurant r = null;
        if(u instanceof Bartender)
        {
            r = ((Bartender) u).getRestaurant();
        }
        else if(u instanceof Cook)
        {
            r = ((Cook) u).getRestaurant();
        }
        Set<Long> ordersId=new HashSet<Long>();
        for(Order o:r.getOrders())
        {
            if(!o.getFinished())
            {
                for(OrderItem oi:o.getOrderItems())
                {
                    if(oi.getFinished()==false && oi.getPreparing()==true)
                    {
                        if(oi.getUserId()==userId)
                        {
                            ordersId.add(o.getId());
                        }
                    }
                }
            }
        }
        return ordersId;
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
    public HistoryDTO makeMark(HistoryDTO historyDTO, Long guestId)
    {
        Restaurant restaurant = restaurantRepository.findById(historyDTO.getRestaurantId());
        Guest guest = (Guest) userRepository.findById(guestId);
        RestaurantMark rm = null;
        rm = restaurantMarkRepository.getMarkByGuestIdAndRestaurantId(guestId, historyDTO.getRestaurantId());
        if(rm!=null)
        {
            rm.setValue((rm.getValue()+historyDTO.getRestaurantMyMark())/2);
            restaurantMarkRepository.save(rm);
        }
        else
        {
            rm = new RestaurantMark(historyDTO.getRestaurantMyMark(),guest, restaurant);
            RestaurantMark savedRestaurantMark=restaurantMarkRepository.save(rm);
            restaurant.addRestaurantMark(savedRestaurantMark);
            restaurantRepository.save(restaurant);
        }
        Waiter waiter = (Waiter) userRepository.findById(historyDTO.getWaiterId());
        Boolean flag=false;
        for(WaiterMark wm : waiter.getWaiterMarks())
        {
            if(wm.getGuest().getId()==guestId)
            {
                wm.setValue((wm.getValue()+historyDTO.getWaiterMark())/2);
                waiterMarkRepository.save(wm);
                flag=true;
                break;
            }
        }
        if(!flag)
        {
            WaiterMark wm = new WaiterMark(historyDTO.getWaiterMark(), guest , waiter);
            WaiterMark savedWaiterMark=waiterMarkRepository.save(wm);
            waiter.addWaiterMark(savedWaiterMark);
            userRepository.save(waiter);
        }
        for(Order order : restaurant.getOrders())
        {
            if(order.getId()==historyDTO.getOrderId())
            {
                order.setMark(historyDTO.getMealMyMark());
                order.setIsMarked(true);
                orderRepository.save(order);
                for(OrderItem oi: order.getOrderItems())
                {
                    if(oi.getIsDish())
                    {
                        flag=false;
                        for(DishMark dm:oi.getDish().getDishMarks())
                        {
                            if(dm.getGuest().getId()==guestId) {
                                dm.setValue((dm.getValue() + historyDTO.getMealMyMark()) / 2);
                                dishMarkRepository.save(dm);
                                flag=true;
                                break;
                            }
                        }
                        if(!flag)
                        {
                            DishMark dishMark = new DishMark(historyDTO.getMealMyMark(),guest,oi.getDish());
                            DishMark savedDishMark = dishMarkRepository.save(dishMark);
                            oi.getDish().addDishMark(savedDishMark);
                            dishRepository.save(oi.getDish());
                        }
                    }
                    else
                    {
                        flag=false;
                        for(DrinkMark dm:oi.getDrink().getDrinkMarks())
                        {
                            if(dm.getGuest().getId()==guestId)
                            {
                                dm.setValue((dm.getValue()+historyDTO.getMealMyMark())/2);
                                drinkMarkRepository.save(dm);
                                flag=true;
                                break;
                            }

                        }
                        if(!flag)
                        {
                            DrinkMark drinkMark = new DrinkMark(historyDTO.getMealMyMark(),guest,oi.getDrink());
                            DrinkMark savedDrinkMark=drinkMarkRepository.save(drinkMark);
                            oi.getDrink().addDrinkMark(savedDrinkMark);
                            drinkRepository.save(oi.getDrink());
                        }
                    }
                }
                break;
            }
        }
        return historyDTO;
    }

    @Override
    public Set<OrderItemDTO> getOrdersForChanging(Long waiterId)
    {
        Waiter waiter=(Waiter) userRepository.findById(waiterId);
        Set<OrderItemDTO> ordersDTO=new HashSet<>();
        for(Order order:waiter.getRestaurant().getOrders())
        {
            if(order.getWaiter().getId()==waiter.getId())
            {
                if(!order.getFinished())
                {
                    Boolean flag=false;
                    for(OrderItem oi:order.getOrderItems())
                    {
                        if(oi.getPreparing())
                        {
                            flag=true;
                            break;
                        }
                    }
                    if(!flag) ordersDTO.add(new OrderItemDTO(order));
                }
            }
        }
        return ordersDTO;
    }


    @Override
    public OrderItemDTO getOrderForChanging(Long waiterId, Long tableId)
    {
        Waiter waiter=(Waiter) userRepository.findById(waiterId);
        OrderItemDTO orderItemDTO=null;
        for(Order order:waiter.getRestaurant().getOrders())
        {
            if(order.getWaiter().getId()==waiterId)
            {
                if(!order.getFinished() && order.getOrderTable().getId()==tableId)
                {
                    Boolean flag=false;
                    for(OrderItem oi:order.getOrderItems())
                    {
                        if(oi.getPreparing())
                        {
                            flag=true;
                            break;
                        }
                    }
                    if(!flag)
                    {
                        orderItemDTO = new OrderItemDTO(order);
                        break;
                    }
                }
            }
        }
        return orderItemDTO;
    }

    @Override
    @Transactional(rollbackFor = OptimisticLockingFailureException.class)
    public Boolean changeOrder(Long waiterId, OrderItemDTO orderDTO) throws OptimisticLockingFailureException
    {
        Order order = orderRepository.findById(orderDTO.getId());
        order.setVersion(orderDTO.getVersion());
        Set<OrderItem> savedOrderItems=new HashSet<OrderItem>();
        for (OrderItmDTO orderItem:orderDTO.getOrderItems())
        {
            OrderItem savedOrderItem=null;
            Drink drink=null;
            Dish dish=null;
            if(orderItem.getIsDish())
            {
                dish=dishRepository.findById(orderItem.getDish().getId());
            }else
            {
                drink=drinkRepository.findById(orderItem.getDrink().getId());
            }
            if(orderItem.getIsDish()){
                savedOrderItem=new OrderItem(dish,orderItem.getNumber());
            }else
            {
                savedOrderItem=new OrderItem(drink,orderItem.getNumber());
            }
            OrderItem saved1=orderItemRepository.save(savedOrderItem);
            savedOrderItems.add(saved1);
        }
        order.setOrderItems(savedOrderItems);
        Order saved = orderRepository.save(order);

        return true;
    }
}
