package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.User;
import com.isa.restaurant.repositories.OrderRepository;
import com.isa.restaurant.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by djuro on 4/22/2017.
 */
public class OrderServiceImpl implements OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    public Order getById(long id)
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

    public Order getByUser(User user)
    {
        Order order=null;
        try
        {
            order = orderRepository.findByUser(user);
        }
        catch (Exception e)
        {
        }
        return order;
    }
}
