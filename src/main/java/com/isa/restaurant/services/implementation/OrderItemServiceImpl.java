package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.repositories.OrderItemRepository;
import com.isa.restaurant.repositories.OrderRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 5/24/2017.
 */
@Service
public class OrderItemServiceImpl implements OrderItemService
{
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

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
    public Boolean preparingItem(Long orderItemId, Long userId)
    {
        OrderItem item=null;
        item=orderItemRepository.findById(orderItemId);
        if(item!=null)
        {
            item.setPreparing(true);
            item.setUserId(userId);
        }
        try
        {
            item = orderItemRepository.save(item);
        }
        catch(Exception e)
        {
        }
        return true;

    }

    @Override
    @Transactional(rollbackFor = OptimisticLockingFailureException.class, isolation = Isolation.SERIALIZABLE)
    public Boolean preparingItem1(Long orderId, Long itemId, Long userId, Long version)
    {
        Order order=orderRepository.findById(orderId);
        OrderItem orderItm = null;
        orderItm = orderItemRepository.findById(itemId);
        if(orderItm!=null)
        {
            orderItm.setPreparing(true);
            orderItm.setUserId(userId);
            orderItemRepository.save(orderItm);
        }
        if (order.getIsChanged()) {
            order.setIsChanged(false);
            orderItemRepository.delete(orderItm);
            order.setIsChanged(false);
            orderRepository.save(order);
            throw new OptimisticLockingFailureException("Attempt to update with wrong version!");
        }
        Boolean flag=false;
        for(OrderItem it:order.getOrderItems())
        {
            if(it.getId()==itemId)
            {
                flag=true;
                break;
            }
        }
        if(flag==false)
        {
            throw new OptimisticLockingFailureException("Attempt to update with wrong version!");
        }
        return true;
    }

    @Override
    public OrderItem finishedItem(Long orderItemId, Long orderId)
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
        Order order=orderRepository.findById(orderId);
        Boolean flag=true;
        for(OrderItem oi:order.getOrderItems())
        {
            System.out.println(oi.getFinished());
            if(!oi.getFinished())
            {
                flag=false;
                break;
            }
        }
        if(flag) {
            order.setFinished(true);
            orderRepository.save(order);
        }
        return item;
    }

    public Set<OrderItem> getPreparingOrderItems(Long userId)
    {
        User u = userRepository.findById(userId);
        Restaurant r = null;
        if(u instanceof Bartender)
        {
            r = ((Bartender) u).getRestaurant();
        }
        else if(u instanceof Cook)
        {
            r = ((Cook) u).getRestaurant();
        }
        Set<OrderItem> orderItems=new HashSet<OrderItem>();
        for(Order o:r.getOrders())
        {
            if(!o.getFinished())
            {
                for(OrderItem oi:o.getOrderItems())
                {
                    System.out.println(oi);
                    if(oi.getFinished()==false && oi.getPreparing()==true)
                    {
                        if(oi.getUserId()==userId)
                        {
                            orderItems.add(oi);
                        }
                    }
                }
            }
        }
        return orderItems;
    }
}
