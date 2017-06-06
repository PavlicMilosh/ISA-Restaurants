package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Milos on 04-Jun-17.
 */
@Entity
@Data
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Table(name = "shopping_item")
public class ShoppingItem
{
    @Id
    @GeneratedValue
    private Long Id;

    @Column(name = "shopping_item_name")
    private String name;

    @Column(name = "shopping_item_amount")
    private String amount;

    public ShoppingItem(String name, String amount)
    {
        this.name = name;
        this.amount = amount;
    }
}
