package com.isa.restaurant.controllers;


import com.isa.restaurant.domain.DTO.OrderDTO;
import com.isa.restaurant.domain.DTO.OrderItemDTO;
import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Drink;
import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.OrderItem;
import com.isa.restaurant.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
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
    private OrderItemService orderItemService;

    @RequestMapping(value = "/{waiterId}/add/{restaurantId}/{tableId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderItemDTO> addOrder(@PathVariable Long restaurantId, @PathVariable Long waiterId, @PathVariable Long tableId, @RequestBody OrderItemDTO order)
    {
        OrderItemDTO saved = ordersService.addOrder(order,restaurantId,waiterId,tableId);
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

    @RequestMapping(value = "/{itemId}/preparing/{userId}/{orderId}/{version}", method = RequestMethod.PUT) //+++
    public ResponseEntity<Boolean> preparingOrderItem(@PathVariable Long itemId, @PathVariable Long userId, @PathVariable Long orderId, @PathVariable Long version)
    {
        Boolean saved;
        try
        {
            saved = orderItemService.preparingItem1(orderId,itemId,userId,version);
        }
        catch (OptimisticLockingFailureException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(saved, HttpStatus.OK);

    }

    @RequestMapping(value = "/{itemId}/finished/{orderId}", method = RequestMethod.PUT)
    public ResponseEntity<OrderItem> finishedOrderItem(@PathVariable Long itemId, @PathVariable Long orderId)
    {
        OrderItem saved = orderItemService.finishedItem(itemId,orderId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{waiterId}/getOrdersForDeliver", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderItemDTO> getOrdersForDeliver(@PathVariable Long waiterId)
    {
        Set<OrderItemDTO> saved = ordersService.getOrdersForDeliver(waiterId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{waiterId)}/getTablesForCreatingBills", method = RequestMethod.GET)
    public ResponseEntity<Set<Long>> getTablesForCreatingBills(@PathVariable Long waiterId)
    {
        Set<Long> saved = ordersService.getTablesForCreatingBill(waiterId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{orderId}/delivered/{userId}", method = RequestMethod.PUT) //+++
    public ResponseEntity<OrderItemDTO> deliveredOrder(@PathVariable Long orderId, @PathVariable Long userId)
    {
        OrderItemDTO saved = ordersService.deliveredOrder(orderId, userId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/getPreparingItems", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //+++
    public ResponseEntity<Set<OrderItem>> getPreparingOrderItems(@PathVariable Long userId)
    {
        Set<OrderItem> saved = orderItemService.getPreparingOrderItems(userId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/getPreparedOrdersId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //+++
    public ResponseEntity<Set<Long>> getPreparedOrdersId(@PathVariable Long userId)
    {
        Set<Long> saved = ordersService.getPreparingOrderId(userId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/getOrdersForChanging", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //+++
    public ResponseEntity<Set<OrderItemDTO>> getOrdersForChanging(@PathVariable Long userId)
    {
        Set<OrderItemDTO>saved = ordersService.getOrdersForChanging(userId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/getOrderForChanging/{tableId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) //+++
    public ResponseEntity<OrderItemDTO> getOrderForChanging(@PathVariable Long userId, @PathVariable Long tableId)
    {
        OrderItemDTO saved = ordersService.getOrderForChanging(userId,tableId);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{waiterId}/changeOrder", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE) //+++
    public ResponseEntity<Boolean> changeOrderOrder(@PathVariable Long waiterId, @RequestBody OrderItemDTO orderDTO)
    {
        Boolean saved;
        try
        {
            saved = ordersService.changeOrder(waiterId, orderDTO);
        }
        catch (OptimisticLockingFailureException e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity(saved, HttpStatus.OK);
    }
}
