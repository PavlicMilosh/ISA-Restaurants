package com.isa.restaurant.services;

import com.isa.restaurant.domain.OrderItem;

/**
 * Created by djuro on 5/24/2017.
 */
public interface OrderItemService
{
    public OrderItem addOrderItem(OrderItem orderItem);

    public OrderItem preparingItem(Long orderItemId);

    public OrderItem finishedItem(Long orderItemId);
}
