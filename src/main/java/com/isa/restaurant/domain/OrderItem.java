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
    private Integer number;

    @Column (name = "order_item_preparing")
    private Boolean preparing;

    @Column (name = "order_item_finished")
    private Boolean finished;


    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (!(other instanceof OrderItem)) return false;

        OrderItem orderItem = (OrderItem) other;

        if (id != null && !id.equals(orderItem.id)) return false;
        if (dish != null ? !dish.equals(orderItem.dish) : orderItem.dish != null) return false;
        if (drink != null ? !drink.equals(orderItem.drink) : orderItem.drink != null) return false;
        if (finished != null ? !finished.equals(orderItem.finished) : orderItem.finished != null) return false;
        if (isDish != null ? !isDish.equals(orderItem.isDish) : orderItem.isDish != null) return false;
        if (number != null ? !number.equals(orderItem.number) : orderItem.number != null) return false;
        if (preparing != null ? !preparing.equals(orderItem.preparing) : orderItem.preparing != null) return false;
        if (finished != null ? !finished.equals(orderItem.finished) : orderItem.finished != null) return false;
        return true;

    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (dish != null ? dish.hashCode() : 0);
        result = 31 * result + (drink != null ? drink.hashCode() : 0);
        result = 31 * result + (finished != null ? finished.hashCode() : 0);
        result = 31 * result + (isDish != null ? isDish.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        result = 31 * result + (preparing != null ? preparing.hashCode() : 0);
        result = 31 * result + (finished != null ? finished.hashCode() : 0);
        return result;
    }


    public OrderItem(Dish dish, int number)
    {
        this.dish = dish;
        this.drink = null;
        this.isDish = true;
        this.number = number;
        this.preparing = false;
        this.finished = false;
    }

    public OrderItem(Drink drink, int number)
    {
        this.dish = null;
        this.drink = drink;
        this.isDish = false;
        this.number = number;
        this.preparing = false;
        this.finished = false;
    }

    public OrderItem(Drink drink, int number, Guest guest)
    {
        this.dish = null;
        this.drink = drink;
        this.isDish = false;
        this.number = number;
        this.preparing = false;
        this.finished = false;
    }
}
