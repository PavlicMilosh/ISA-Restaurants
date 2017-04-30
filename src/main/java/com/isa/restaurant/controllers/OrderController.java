package com.isa.restaurant.controllers;


import com.isa.restaurant.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.isa.restaurant.services.OrdersService;

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

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        Order saved = ordersService.addOrder(order);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}/finish", method = RequestMethod.PUT)
    public ResponseEntity<Order> finishOrder(@PathVariable Long orderId){
        Order saved = ordersService.finishOrder(orderId);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.OK);

    }

}
