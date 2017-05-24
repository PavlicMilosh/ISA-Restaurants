package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.OrderItem;
import com.isa.restaurant.repositories.OrderItemRepository;
import com.isa.restaurant.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djuro on 5/24/2017.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService
{
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public OrderItem addOrderItem(OrderItem orderItem)
    {
        OrderItem saved = null;
        try
        {
            saved = orderItemRepository.save(orderItem);
        }
        catch(Exception e)
        {
        }
        return saved;
    }

    @Override
    public OrderItem preparingItem(Long orderItemId)
    {
        OrderItem item=null;
        item=orderItemRepository.findById(orderItemId);
        if(item!=null)
        {
            item.setPreparing(true);
        }
        try
        {
            item = orderItemRepository.save(item);
        }
        catch(Exception e)
        {
        }
        return item;

    }

    @Override
    public OrderItem finishedItem(Long orderItemId)
    {
        OrderItem item=null;
        item=orderItemRepository.findById(orderItemId);
        if(item!=null)
        {
            item.setFinished(true);
        }
        try
        {
            item = orderItemRepository.save(item);
        }
        catch(Exception e)
        {
        }
        return item;

    }
}
