package com.isa.restaurant.services;

import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Drink;
import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.RestaurantOrders;

import java.util.Set;

/**
 * Created by djuro on 5/24/2017.
 */
public interface RestaurantOrdersService {
    Order addOrder(Order order, Long id);

    Set<Dish> getAllDishes(Long id);

    Set<Drink> getAllDrinks(Long id);

    Set<Order> getAllOrders(Long id);

    RestaurantOrders addRestaurantOrders(Long id);
}
