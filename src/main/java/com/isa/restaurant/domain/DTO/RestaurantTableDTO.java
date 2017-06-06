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
    private Boolean occupied;
    private Long regionId;
    private String regionColor;
    private Integer seats;
    private RestaurantDTO restaurantDTO;


    public RestaurantTableDTO(RestaurantTable restaurantTable, Boolean occupied)
    {
        this.id = restaurantTable.getId();
        this.top = restaurantTable.getTop();
        this.left = restaurantTable.getLeft();
        this.angle = restaurantTable.getAngle();
        this.occupied = occupied;
        this.regionId = restaurantTable.getRegion().getId();
        this.regionColor = restaurantTable.getRegion().getColor();
        this.seats = restaurantTable.getSeats();
        this.restaurantDTO = new RestaurantDTO(restaurantTable.getRestaurant());
    }
}

