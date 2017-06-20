package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Drink;
import com.isa.restaurant.domain.Waiter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by Milos on 18-Jun-17.
 */
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Getter
@Setter
public class Report
{
    private HashSet<Dish> dishes;
    private HashSet<Drink> drinks;
    private HashSet<Waiter> waiters;
    private Double restaurantMark;
}
