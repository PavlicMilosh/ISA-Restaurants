package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Bill;
import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.OrderItem;
import com.isa.restaurant.domain.User;
import com.isa.restaurant.repositories.BillRepository;
import com.isa.restaurant.repositories.OrderRepository;
import com.isa.restaurant.services.OrderItemService;
import com.isa.restaurant.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 4/22/2017.
 */
@Service
public class OrdersServiceImpl implements OrdersService
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private OrderItemService orderItemService;

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
    public Order addOrder(Order order)
    {
        Order saved = null;
        try
        {
            Set<OrderItem> savedOrderItems=new HashSet<OrderItem>();
            for (OrderItem orderItem:order.getOrderItems())
            {
                OrderItem savedOrderItem=orderItemService.addOrderItem(orderItem);
                savedOrderItems.add(savedOrderItem);
            }
            order.setOrderItems(savedOrderItems);
            saved = orderRepository.save(order);
        }
        catch(Exception e)
        {
        }
        return saved;
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
}
