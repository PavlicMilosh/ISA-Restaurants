package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.OrderItem;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by djuro on 6/22/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderItemRepositoryTest
{
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void testFindById()
    {
        OrderItem orderItem=new OrderItem();
        orderItemRepository.save(orderItem);
        OrderItem getOrderItem = orderItemRepository.findById(orderItem.getId());
        Assert.assertEquals(getOrderItem, orderItem);
    }
}
