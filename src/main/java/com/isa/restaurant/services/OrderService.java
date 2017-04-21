package com.isa.restaurant.services;

import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.User;

/**
 * Created by djuro on 4/22/2017.
 */
public interface OrderService
{
    public Order getById(long id);

    public Order getByUser(User user);
}
