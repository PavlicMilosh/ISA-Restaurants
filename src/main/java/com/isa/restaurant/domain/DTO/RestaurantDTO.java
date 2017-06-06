package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Q on 13-May-17.
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDTO
{
    private Long id;
    private String name;
    private String description;
    private Double friendsMark;
    private Double meanMark;


    public RestaurantDTO(Restaurant restaurant)
    {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.description = restaurant.getDescription();
        this.friendsMark = 0.0;
        this.meanMark = 0.0;
    }


    public RestaurantDTO(Restaurant restaurant, Double meanMark, Double friendsMark)
    {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.description = restaurant.getDescription();
        this.friendsMark = meanMark;
        this.meanMark = friendsMark;
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
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (meanMark != null ? meanMark.hashCode() : 0);
        result = 31 * result + (friendsMark != null ? friendsMark.hashCode() : 0);
        return result;
    }
}
