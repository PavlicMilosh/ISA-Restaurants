package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by djuro on 4/21/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restaurant_bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_id", unique = true, nullable = false)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "order_id", name = "bill_orders_id")
    private Set<Order> orders;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "bill_user1")
    private User user;

    @Column(name = "bill_price")
    private Double price;

    @ManyToOne
    private RestaurantTable restaurantTable;

    public Bill(User user){
        this.user=user;
        this.orders=new HashSet<>();
    }

    public void calculateBillPrice()
    {
        for (Order order: orders) this.price += order.getPrice();
    }


    public void addOrder(Order order)
    {
        this.orders.add(order);
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Bill)) return false;

        Bill bill = (Bill) o;

        if (id != null ? !id.equals(bill.id) : false) return false;
        if (orders != null ? !orders.equals(bill.orders) : bill.orders != null) return false;
        if (user != null ? !user.equals(bill.user) : bill.user != null) return false;
        if (price != null ? !price.equals(bill.price) : bill.price != null) return false;
        if (restaurantTable != null ? !restaurantTable.equals(bill.restaurantTable) : bill.restaurantTable != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (restaurantTable != null ? restaurantTable.hashCode() : 0);
        return result;
    }
}
