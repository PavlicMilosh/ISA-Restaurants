package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Q on 06-Jun-17.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
@Entity
@Table(name = "dish_mark",
        uniqueConstraints = @UniqueConstraint(columnNames = {"dish_mark_guest_id", "dish_mark_dish_id"}))
public class DishMark
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "dish_mark_id")
    private Long id;

    @Column(name = "dish_mark_value")
    private Double value;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "dish_mark_guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(referencedColumnName = "dish_id", name = "dish_mark_dish_id")
    private Dish dish;
}
