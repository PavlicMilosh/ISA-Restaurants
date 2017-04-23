package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by djuro on 4/21/2017.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restaurant_order")
public class Order
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", unique = true, nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "dish_id", name = "order_dishes_id")
    private Set<Dish> dishes;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "drink_id", name = "order_drink_id")
    private Set<Drink> drinks;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "order_user")
    private User user;

    @Column(name = "order_finished")
    private Boolean finished;

    @Column(name = "order_price")
    private Double price;

    @ManyToOne
    private Bill bill;

    public Order(User user)
    {
        this.price=0.0;
        this.user=user;
        this.finished=false;
        this.drinks=new HashSet<Drink>();
        this.dishes=new HashSet<Dish>();
    }


    public void calculateOrderPrice()
    {
        for (Dish dish: dishes) this.price += dish.getPrice();
        for (Drink drink: drinks) this.price += drink.getPrice();
    }

}
