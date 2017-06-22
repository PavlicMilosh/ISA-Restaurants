package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Drink;
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
public class DrinkOrderDTO
{
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    public DrinkOrderDTO(Drink drink, Integer quantity)
    {
        this.id = drink.getId();
        this.name = drink.getName();
        this.description = drink.getDescription();
        this.price = drink.getPrice();
        this.quantity = quantity;
    }

    public DrinkOrderDTO(Drink drink)
    {
        this.id = drink.getId();
        this.name = drink.getName();
        this.description = drink.getDescription();
        this.price = drink.getPrice();
    }
}
