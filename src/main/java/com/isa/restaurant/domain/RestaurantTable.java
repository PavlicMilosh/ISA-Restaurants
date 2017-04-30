package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Milos on 13-Apr-17.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "restaurant_table")
public class RestaurantTable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "top_coord")
    private Double top;

    @Column(name = "left_coord")
    private Double left;

    @Column(name = "angle")
    private double angle;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantTable", fetch = FetchType.LAZY)
    private Set<Bill> Bills;
}
