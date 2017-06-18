package com.isa.restaurant.services;

import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.User;

/**
 * Created by djuro on 4/22/2017.
 */
public interface OrdersService
{
    public Order getById(Long id);

    public Order getByUser(User user);

    public Order addOrder(Order order, Long tableId);

    public Order finishOrder(Long id);
}
