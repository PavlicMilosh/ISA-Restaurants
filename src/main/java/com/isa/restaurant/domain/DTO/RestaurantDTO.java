package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Q on 13-May-17.
 */

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
public class RestaurantDTO
{
    private Long id;
    private String name;
    private String description;
    private Double friendsMark;
    private Double meanMark;
    private Address address;
    private Integer visits;
    private Set<Drink> drinks;
    private Set<Dish> dishes;
    private Set<RestaurantTableDTO> tables;


    public RestaurantDTO()
    {
        this.drinks = new HashSet<>();
        this.dishes = new HashSet<>();
        this.tables = new HashSet<>();
    }


    public RestaurantDTO(Restaurant restaurant)
    {
        this();
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.description = restaurant.getDescription();
        this.address = restaurant.getAddress();

        this.friendsMark = 0.0;
        this.meanMark = 0.0;
        this.drinks = restaurant.getDrinks();
        this.dishes = restaurant.getDishes();
        for (RestaurantTable rt : restaurant.getTables())
            this.tables.add(new RestaurantTableDTO(rt, null));
    }


    public RestaurantDTO(Restaurant restaurant, Double meanMark, Double friendsMark, Integer visits)
    {
        this();
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.description = restaurant.getDescription();
        this.address = restaurant.getAddress();

        this.visits = visits;
        this.friendsMark = meanMark;
        this.meanMark = friendsMark;
        this.drinks = restaurant.getDrinks();
        this.dishes = restaurant.getDishes();

        for (RestaurantTable rt : restaurant.getTables())
            this.tables.add(new RestaurantTableDTO(rt, null));
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof RestaurantDTO)) return false;

        RestaurantDTO restaurantDTO = (RestaurantDTO) o;

        if (id != null && !id.equals(restaurantDTO.id)) return false;
        if (name != null ? !name.equals(restaurantDTO.name) : restaurantDTO.name != null) return false;
        if (description != null ? !description.equals(restaurantDTO.description) : restaurantDTO.description != null) return false;
        if (address != null ? !address.equals(restaurantDTO.address) : restaurantDTO.address != null) return false;
        if (meanMark != null ? !meanMark.equals(restaurantDTO.meanMark) : restaurantDTO.meanMark != null) return false;
        if (friendsMark != null ? !friendsMark.equals(restaurantDTO.friendsMark) : restaurantDTO.friendsMark != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (meanMark != null ? meanMark.hashCode() : 0);
        result = 31 * result + (friendsMark != null ? friendsMark.hashCode() : 0);
        return result;
    }
}
