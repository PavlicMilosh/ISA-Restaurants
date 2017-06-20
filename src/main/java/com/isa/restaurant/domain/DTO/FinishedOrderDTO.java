package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

/**
 * Created by djuro on 6/20/2017.
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class FinishedOrderDTO
{
    private Long id;
    private Set<OrderItem> orderItems;
    private Date orderTime;
    private Long restaurantId;
    private String restaurantName;

    public FinishedOrderDTO(Order order,Long restaurantId, String restaurantName)
    {
        this.id=order.getId();
        this.orderItems=order.getOrderItems();
        this.orderTime=order.getOrderTime();
        this.restaurantId=restaurantId;
        this.restaurantName=restaurantName;
    }

    public FinishedOrderDTO(Order order)
    {
        this.id=order.getId();
        this.orderItems=order.getOrderItems();
        this.orderTime=order.getOrderTime();
    }
}
