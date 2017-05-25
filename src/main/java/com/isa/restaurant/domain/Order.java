package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
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
    @JoinColumn(referencedColumnName = "order_item_id", name = "order_order_item_id")
    private Set<OrderItem> orderItems;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "order_waiter")
    private Waiter waiter;

    @Column(name = "order_finished")
    private Boolean finished;

    @Column(name = "order_price")
    private Double price;

    @Column(name = "order_time")
    private Date orderTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(referencedColumnName = "table_id", name = "restaurant_order_table_id")
    private RestaurantTable orderTable;

    public Order(Waiter waiter)
    {
        this.price=0.0;
        this.waiter=waiter;
        this.finished=false;
        this.orderItems=new HashSet<OrderItem>();
        this.orderTime=null;
        this.orderTable=null;
    }

    public Order(Waiter waiter, HashSet<OrderItem> orderItems, RestaurantTable restaurantTable)
    {
        this.price=0.0;
        this.waiter=waiter;
        this.finished=false;
        this.orderItems=orderItems;
        this.orderTable=restaurantTable;
    }


    public void calculateOrderPrice()
    {
        for(OrderItem orderItem : orderItems)
        {
            if(orderItem.getIsDish())
            {
                this.price += orderItem.getDish().getPrice()*orderItem.getNumber();
            }else
            {
                this.price += orderItem.getDrink().getPrice()*orderItem.getNumber();
            }
        }
    }

    public void isFinished()
    {
        Boolean finished=true;
        for (OrderItem item:this.orderItems)
        {
            if(item.getFinished()==false)
            {
                finished=false;
                break;
            }
        }
        this.finished=finished;
    }

}
