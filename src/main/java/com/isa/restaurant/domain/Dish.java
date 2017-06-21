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
@Table(name = "dish")
public class Dish
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "dish_name", unique = true)
    private String name;

    @Column(name = "dish_desc")
    private String description;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @Column(name = "dish_price")
    private Double price;

    @OneToMany
    private Set<DishMark> dishMarks;

    @ManyToOne
    @JoinColumn(name = "dish_type_id")
    private DishType dishType;

    public Dish(String name, String description, Double price, Restaurant restaurant)
    {
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurant;
    }
}
