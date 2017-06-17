package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Q on 16-Jun-17.
 */
@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
public class OrderDTO
{
    private Long id;
    private Set<DrinkOrderDTO> drinkOrders;
    private Set<DishOrderDTO> dishOrders;
    private GuestDTO guest;


    public OrderDTO()
    {
        this.drinkOrders = new HashSet<>();
        this.dishOrders = new HashSet<>();
    }

    public OrderDTO(Order order)
    {
        this();
        this.id = order.getId();

        for (OrderItem orderItem : order.getOrderItems())
        {
            if (orderItem.getIsDish())
                dishOrders.add(new DishOrderDTO(orderItem.getDish(), orderItem.getNumber()));
            else
                drinkOrders.add(new DrinkOrderDTO(orderItem.getDrink(), orderItem.getNumber()));
        }

        this.guest = new GuestDTO(order.getGuest());
    }
}
