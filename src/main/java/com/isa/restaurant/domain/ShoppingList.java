package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Milos on 04-Jun-17.
 */
@Entity
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
@Table(name = "shoppingList")
public class ShoppingList
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "deadline")
    private Date deadline;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ShoppingItem> items;

    @ManyToOne
    private Restaurant restaurant;

    public ShoppingList()
    {
        this.items = new HashSet<>();
    }

    public void addItem(ShoppingItem shoppingItem)
    {
        this.items.add(shoppingItem);
    }
}
