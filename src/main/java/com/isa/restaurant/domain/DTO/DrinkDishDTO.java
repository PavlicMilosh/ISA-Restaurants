package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Drink;

/**
 * Created by djuro on 6/20/2017.
 */
public class DrinkDishDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Boolean isDish;
    private Double mark;

    public DrinkDishDTO(Drink drink, Double mark) {
        this.id = drink.getId();
        this.name = drink.getName();
        this.description = drink.getDescription();
        this.price = drink.getPrice();
        this.isDish = false;
        this.mark = mark;
    }

    public DrinkDishDTO(Dish dish, Double mark) {
        this.id = dish.getId();
        this.name = dish.getName();
        this.description = dish.getDescription();
        this.price = dish.getPrice();
        this.isDish = true;
        this.mark = mark;
    }

}
