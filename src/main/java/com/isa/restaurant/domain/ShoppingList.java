package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Milos on 04-Jun-17.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
@Table(name = "shoppingList")
public class ShoppingList
{
    @Id
    @GeneratedValue
    @Column(name = "shopping_list_id")
    private Long id;

    @Column(name = "shopping_list_name")
    private String name;

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn(name="accepted_offer_id", unique= true)
    private Offer acceptedOffer;

    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "CET")
    private Date deadline;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "shoppingList")
    private Set<ShoppingItem> items;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Offer> offers;

    public ShoppingList()
    {
        this.acceptedOffer = null;
        this.items = new HashSet<>();
    }

    public void addItem(ShoppingItem shoppingItem)
    {
        this.items.add(shoppingItem);
    }
}
