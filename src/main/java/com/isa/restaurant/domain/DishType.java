package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by djuro on 5/24/2017.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "dish_type")
public class DishType
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dish_type_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(referencedColumnName = "restaurant_id", name = "dish_type_restaurant_id")
    private Restaurant restaurant;

    @Column(name = "dish_type_name")
    private String name;

    public DishType(Restaurant restaurant, String name)
    {
        this.restaurant=restaurant;
        this.name=name;
    }
}
