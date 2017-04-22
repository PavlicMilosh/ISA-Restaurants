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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_id", unique = true, nullable = false)
    private Long id;

    //@OneToMany(mappedBy = "Bill", fetch = FetchType.LAZY)
    //private Set<Order> orders;

    //@OneToOne(mappedBy = "Bill", fetch = FetchType.LAZY)
    //private User user;

    @Column(name = "bill_price")
    private Double price;

    public Bill(User user){
        //this.user=user;
        //this.orders=new HashSet<>();
    }

    public void calculateBillPrice()
    {
        // for (Order order: orders) this.price += order.getPrice();
    }
}
