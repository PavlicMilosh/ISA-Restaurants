package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 5/24/2017.
 */
@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "restaurant_orders")
public class RestaurantOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orders_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "restaurant_orders_order_id")
    private Long restaurantId;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "dish_id", name = "orders_dishes_id")
    private Set<Dish> dishes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "drink_id", name = "orders_drinks_id")
    private Set<Drink> drinks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "order_id", name = "restaurant_orders_orders_id")
    private Set<Order> orders;

    public RestaurantOrders()
    {
        this.drinks=new HashSet<Drink>();
        this.dishes=new HashSet<Dish>();
        this.orders=new HashSet<Order>();
    }

    public RestaurantOrders(Long restaurantId)
    {
        this.drinks=new HashSet<Drink>();
        this.dishes=new HashSet<Dish>();
        this.orders=new HashSet<Order>();
        this.restaurantId=restaurantId;
    }

    public void addOrder(Order order)
    {
        orders.add(order);
    }
}
