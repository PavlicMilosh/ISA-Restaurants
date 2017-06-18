package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Milos on 10-Jun-17.
 */
@NoArgsConstructor
@Getter
@Setter
@Table(name = "offer", uniqueConstraints = @UniqueConstraint(columnNames = {"provider_user_id", "shopping_list_shopping_list_id"}))
@Entity
public class Offer
{
    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Column(name = "amount")
    private Long amount;

    @ManyToOne
    @JsonIgnore
    private ShoppingList shoppingList;

    @ManyToOne
    private Provider provider;
}
