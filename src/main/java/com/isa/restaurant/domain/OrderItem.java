package com.isa.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;

/**
 * Created by djuro on 5/24/2017.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_item_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "dish_id", name = "order_item_dish_id")
    private Dish dish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "drink_id", name = "order_item_drink_id")
    private Drink drink;

    @Column(name = "is_dish")
    private Boolean isDish;

    @Column(name = "number_of_orders")
    private int number;

    @Column (name = "order_item_preparing")
    private Boolean preparing;

    @Column (name = "order_item_finished")
    private Boolean finished;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "order_item_guest_id")
    private Guest guest;


    public OrderItem(Dish dish, int number)
    {
        this.dish = dish;
        this.drink = null;
        this.isDish = true;
        this.number = number;
        this.preparing = false;
        this.finished = false;
        this.guest = null;
    }

    public OrderItem(Drink drink, int number)
    {
        this.dish = null;
        this.drink = drink;
        this.isDish = false;
        this.number = number;
        this.preparing = false;
        this.finished = false;
        this.guest = guest;
    }

    public OrderItem(Drink drink, int number, Guest guest)
    {
        this.dish = null;
        this.drink = drink;
        this.isDish = false;
        this.number = number;
        this.preparing = false;
        this.finished = false;
        this.guest = guest;
    }
}
