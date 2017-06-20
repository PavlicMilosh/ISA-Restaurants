package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.util.*;

/**
 * Created by djuro on 4/21/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
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

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "order_item_guest_id")
    private Guest guest;

    @Column(name = "order_mark")
    private Double mark;

    @Column(name="order_delivered")
    private Boolean delivered;

    @Column(name="order_bill_created")
    private Boolean billCreated;

    @Column(name="order_is_marked")
    private Boolean isMarked;

    public Order(Waiter waiter)
    {
        this.price=0.0;
        this.waiter=waiter;
        this.finished=false;
        this.orderItems=new HashSet<>();
        this.orderTime=null;
        this.orderTable=null;
        this.delivered=false;
        this.billCreated=false;
        this.isMarked=false;
    }

    public Order(Waiter waiter, HashSet<OrderItem> orderItems, RestaurantTable restaurantTable)
    {
        this.price=0.0;
        this.waiter=waiter;
        this.finished=false;
        this.orderItems=orderItems;
        this.orderTable=restaurantTable;
        this.delivered=false;
        this.billCreated=false;
        this.isMarked=false;
    }

    public Order(HashSet<OrderItem> orderItems, Date orderTime)
    {
        this.orderItems = orderItems;
        this.price = 0.0;
        this.calculateOrderPrice();
        this.finished = false;
        this.orderTime = orderTime;
        this.delivered=false;
        this.billCreated=false;
        this.isMarked=false;
    }

    public Order(Set<OrderItem> orderItems, Date orderTime)
    {
        this.orderItems = orderItems;
        this.price = 0.0;
        this.calculateOrderPrice();
        this.finished = false;
        this.orderTime = orderTime;
        this.delivered=false;
        this.billCreated=false;
        this.isMarked=false;
    }


    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (!(other instanceof Order)) return false;

        Order order = (Order) other;

        if (id != null && !id.equals(order.id)) return false;
        if (orderItems != null ? !orderItems.equals(order.orderItems) : order.orderItems != null) return false;
        if (waiter != null ? !waiter.equals(order.waiter) : order.waiter != null) return false;
        if (finished != null ? !finished.equals(order.finished) : order.finished != null) return false;
        if (price != null ? !price.equals(order.price) : order.price != null) return false;
        if (orderTime != null ? !orderTime.equals(order.orderTime) : order.orderTime != null) return false;
        if (orderTable != null ? !orderTable.equals(order.orderTable) : order.orderTable != null) return false;
        if (guest != null ? !guest.equals(order.guest) : order.guest != null) return false;
        if (mark != null ? !mark.equals(order.mark) : order.mark != null) return false;
        if (delivered != null ? !delivered.equals(order.delivered) : order.delivered != null) return false;
        if (billCreated != null ? !billCreated.equals(order.billCreated) : order.billCreated != null) return false;
        if (isMarked != null ? !isMarked.equals(order.isMarked) : order.isMarked != null) return false;
        return true;

    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (orderItems != null ? orderItems.hashCode() : 0);
        result = 31 * result + (waiter != null ? waiter.hashCode() : 0);
        result = 31 * result + (finished != null ? finished.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (orderTime != null ? orderTime.hashCode() : 0);
        result = 31 * result + (orderTable != null ? orderTable.hashCode() : 0);
        result = 31 * result + (guest != null ? guest.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (delivered  != null ? delivered.hashCode() : 0);
        result = 31 * result + (billCreated  != null ? billCreated.hashCode() : 0);
        result = 31 * result + (isMarked  != null ? isMarked.hashCode() : 0);
        return result;
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
