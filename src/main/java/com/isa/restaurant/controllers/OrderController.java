package com.isa.restaurant.controllers;


import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Drink;
import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.OrderItem;
import com.isa.restaurant.services.OrderItemService;
import com.isa.restaurant.services.RestaurantOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isa.restaurant.services.OrdersService;

import java.util.Set;

/**
 * Created by djuro on 4/24/2017.
 */
@RestController
@RequestMapping(value = "/order")
@CrossOrigin
public class OrderController
{
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private RestaurantOrdersService restaurantOrdersService;

    @Autowired
    private OrderItemService orderItemService;

    @RequestMapping(value = "/{waiterId}/add/{restaurantId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> addOrder(@PathVariable Long restaurantId, @PathVariable Long waiterId, @RequestBody Order order)
    {
        Order saved = restaurantOrdersService.addOrder(order,restaurantId,waiterId);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}/finish", method = RequestMethod.PUT)
    public ResponseEntity<Order> finishOrder(@PathVariable Long orderId)
    {
        Order saved = ordersService.finishOrder(orderId);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.OK);

    }

    @RequestMapping(value = "/{ordersId}/getAllDishes")
    public ResponseEntity<Set<Dish>> getAllDishes(@PathVariable Long ordersId)
    {
        Set<Dish> dishes = restaurantOrdersService.getAllDishes(ordersId);
        return new ResponseEntity(dishes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{ordersId}/getAllDrinks")
    public ResponseEntity<Set<Drink>> getAllDrinks(@PathVariable Long ordersId)
    {
        Set<Drink> drinks = restaurantOrdersService.getAllDrinks(ordersId);
        return new ResponseEntity(drinks, HttpStatus.OK);
    }

    @RequestMapping(value = "/{restaurantId}/getOrders", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Order>> getAllOrders(@PathVariable Long restaurantId)
    {
        Set<Order> orders = restaurantOrdersService.getAllOrders(restaurantId);
        if(orders == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{itemId}/preparing", method = RequestMethod.GET)
    public ResponseEntity<OrderItem> preparingOrderItem(@PathVariable Long itemId)
    {
        OrderItem saved = orderItemService.preparingItem(itemId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{itemId}/finished", method = RequestMethod.GET)
    public ResponseEntity<OrderItem> finishedOrderItem(@PathVariable Long itemId)
    {
        OrderItem saved = orderItemService.finishedItem(itemId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

}
