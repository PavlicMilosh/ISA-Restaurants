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
@AllArgsConstructor
@Entity
@Table(name = "restaurant_mark",
       uniqueConstraints = @UniqueConstraint(columnNames = {"restaurant_mark_guest_id", "restaurant_mark_restaurant_id"}))
public class RestaurantMark
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "restaurant_mark_id")
    private Long id;

    @Column(name = "restaurant_mark_value")
    private Double value;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "restaurant_mark_guest_id")
    private Guest guest;

    @ManyToOne
    @JoinColumn(referencedColumnName = "restaurant_id", name = "restaurant_mark_restaurant_id")
    private Restaurant restaurant;
}
