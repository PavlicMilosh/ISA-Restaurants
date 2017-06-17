package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Q on 10-Jun-17.
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class DishOrderDTO
{
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    public DishOrderDTO(Dish dish, Integer quantity)
    {
        this.id = dish.getId();
        this.name = dish.getName();
        this.description = dish.getDescription();
        this.price = dish.getPrice();
        this.quantity = quantity;
    }
}
