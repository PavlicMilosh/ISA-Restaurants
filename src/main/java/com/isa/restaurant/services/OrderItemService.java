package com.isa.restaurant.services;

import com.isa.restaurant.domain.OrderItem;

import java.util.Set;

/**
 * Created by djuro on 5/24/2017.
 */
public interface OrderItemService
{
    public OrderItem addOrderItem(OrderItem orderItem);

    public Boolean preparingItem(Long orderItemId, Long userId);

    public Boolean preparingItem1(Long orderId, Long itemId, Long userId, Long version);

    public OrderItem finishedItem(Long orderItemId, Long orderId);

    public Set<OrderItem> getPreparingOrderItems(Long userId);
}
