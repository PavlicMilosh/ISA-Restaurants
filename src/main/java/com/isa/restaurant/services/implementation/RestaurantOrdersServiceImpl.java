package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.repositories.RestaurantOrdersRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.OrdersService;
import com.isa.restaurant.services.RestaurantOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by djuro on 5/24/2017.
 */
@Service
public class RestaurantOrdersServiceImpl implements RestaurantOrdersService
{
    @Autowired
    private RestaurantOrdersRepository restaurantOrdersRepository;

    @Autowired
    private OrdersService orderService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Order addOrder(Order order, Long id, Long waiterId){
        Waiter w=(Waiter) userRepository.findById(waiterId);
        order.setWaiter(w);
        Order saved = orderService.addOrder(order);
        RestaurantOrders restaurantOrders= restaurantOrdersRepository.findByRestaurantId(id);
        if(restaurantOrders != null)
        {
            restaurantOrders.addOrder(saved);
        }
        RestaurantOrders savedRestaurantOrders = restaurantOrdersRepository.save(restaurantOrders);
        return saved;
    }

    @Override
    public Set<Dish> getAllDishes(Long id)
    {
        RestaurantOrders restaurantOrders=restaurantOrdersRepository.findById(id);
        Set<Dish> dishes = null;
        if(restaurantOrders != null)
        {
            dishes = restaurantOrders.getDishes();
        }
        return dishes;
    }

    @Override
    public Set<Drink> getAllDrinks(Long id)
    {
        RestaurantOrders restaurantOrders=restaurantOrdersRepository.findById(id);
        Set<Drink> drinks = null;
        if(restaurantOrders != null)
        {
            drinks = restaurantOrders.getDrinks();
        }
        return drinks;
    }

    @Override
    public Set<Order> getAllOrders(Long id)
    {
        RestaurantOrders restaurantOrders=restaurantOrdersRepository.findByRestaurantId(id);
        Set<Order> orders = null;
        if(restaurantOrders != null)
        {
            orders = restaurantOrders.getOrders();
        }
        return orders;
    }

    @Override
    public RestaurantOrders addRestaurantOrders(Long id)
    {
        RestaurantOrders restaurantOrders=new RestaurantOrders(id);
        RestaurantOrders saved= restaurantOrdersRepository.save(restaurantOrders);
        return saved;
    }
}
