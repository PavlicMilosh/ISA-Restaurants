package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Milos on 12-Apr-17.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drink")
public class Drink
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "drink_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "drink_name", unique = true)
    private String name;

    @Column(name = "drink_desc")
    private String description;

    @Column(name = "drink_price")
    private Double price;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany
    private Set<DrinkMark> drinkMarks;

    public Drink(String name, String description, Double price, Restaurant restaurant)
    {
        this.name=name;
        this.description=description;
        this.price=price;
        this.restaurant=restaurant;
    }
}
