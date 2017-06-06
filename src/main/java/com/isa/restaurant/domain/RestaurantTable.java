package com.isa.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.isa.restaurant.domain.DTO.RestaurantTableDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

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

    @Column(name = "table_top_coord")
    private Double top;

    @Column(name = "table_left_coord")
    private Double left;

    @Column(name = "table_angle")
    private Double angle;

    @Column(name = "table_seats")
    private Integer seats;

    @ManyToOne
    @JsonIgnore
    private Restaurant restaurant;

    @OneToMany(mappedBy = "restaurantTable", fetch = FetchType.LAZY)
    private Set<Bill> bills;

    @ManyToOne
    @JsonIgnore
    private Region region;


    public RestaurantTable(Double top, Double left, Double angle, Region region, Set<Bill> bills)
    {
        this.top = top;
        this.left = left;
        this.angle = angle;
        this.region = region;
        this.bills = bills;
    }


    public RestaurantTable(RestaurantTableDTO rtDTO)
    {
        this.id = rtDTO.getId();
        this.top = rtDTO.getTop();
        this.left = rtDTO.getTop();
        this.angle = rtDTO.getTop();
        this.restaurant = new Restaurant(rtDTO.getRestaurant());
    }
}
