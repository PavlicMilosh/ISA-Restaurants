package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.RestaurantTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;

/**
 * Created by Q on 16-May-17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantTableDTO
{
    private Long id;
    private Double top;
    private Double left;
    private Double angle;
    private RestaurantDTO restaurant;
    private Boolean occupied;


    public RestaurantTableDTO(RestaurantTable restaurantTable, Boolean occupied)
    {
        this.id = restaurantTable.getId();
        this.top = restaurantTable.getTop();
        this.left = restaurantTable.getLeft();
        this.angle = restaurantTable.getAngle();
        this.restaurant = new RestaurantDTO(restaurantTable.getRestaurant());
        this.occupied = occupied;
    }
}

