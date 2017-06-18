package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.OrderDTO;
import com.isa.restaurant.domain.DTO.OrderItemDTO;
import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Drink;
import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.RestaurantOrders;

import java.util.Set;

/**
 * Created by djuro on 5/24/2017.
 */
public interface RestaurantOrdersService
{
    OrderItemDTO addOrder(OrderItemDTO order, Long id, Long waiterId, Long tableId);

    Set<Dish> getAllDishes(Long id);

    Set<Drink> getAllDrinks(Long id);

    Set<Order> getAllOrders(Long id);

    RestaurantOrders addRestaurantOrders(Long id);
}
