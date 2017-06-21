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
 * Created by djuro on 6/18/2017.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemDTO
{
    private Long id;
    private Set<OrderItem> orderItems;
    private Double price;
    private Boolean finished;
    private Date orderTime;
    private Long tableId;

    public OrderItemDTO(Order order)
    {
        this.id=order.getId();
        this.orderItems=order.getOrderItems();
        this.orderTime=order.getOrderTime();
        this.price=order.getPrice();
        this.finished=order.getFinished();
        this.tableId=order.getOrderTable().getId();
    }
}
