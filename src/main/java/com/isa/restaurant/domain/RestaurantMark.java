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

    public RestaurantMark(Double value, Guest guest, Restaurant restaurant)
    {
        this.value=value;
        this.guest=guest;
        this.restaurant=restaurant;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof RestaurantMark)) return false;

        RestaurantMark restaurantMark = (RestaurantMark) o;

        if (id != null ? !id.equals(restaurantMark.id) : false) return false;
        if (value != null ? !value.equals(restaurantMark.value) : restaurantMark.value != null) return false;
        if (guest != null ? !guest.equals(restaurantMark.guest) : restaurantMark.guest != null) return false;
        if (restaurant != null ? !restaurant.equals(restaurantMark.restaurant) : restaurantMark.restaurant != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (guest != null ? guest.hashCode() : 0);
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        return result;
    }
}
