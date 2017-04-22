package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by Milos on 13-Apr-17.
 */
@Entity
@AllArgsConstructor
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

    @Column(name = "x_coord")
    private Double xCoord;

    @Column(name = "y_coord")
    private Double yCoord;

    @ManyToOne
    private Restaurant restaurant;

    //@OneToOne(mappedBy = "RestaurantTable", fetch = FetchType.LAZY)
    //private Bill bill;
}
