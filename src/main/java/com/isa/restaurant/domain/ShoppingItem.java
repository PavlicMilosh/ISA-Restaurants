package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Milos on 04-Jun-17.
 */
@Entity
@Getter
@Setter
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

    @ManyToOne
    @JsonIgnore
    private ShoppingList shoppingList;

    public ShoppingItem(String name, String amount)
    {
        this.name = name;
        this.amount = amount;
    }

    public ShoppingItem(String name, String amount, ShoppingList shoppingList)
    {
        this.name = name;
        this.amount = amount;
        this.shoppingList = shoppingList;
    }
}
