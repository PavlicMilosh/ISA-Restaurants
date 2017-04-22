package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.RestaurantManager;
import com.isa.restaurant.repositories.RestaurantRepository;
import com.isa.restaurant.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Milos on 13-Apr-17.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    public Restaurant addRestaurant(Restaurant restaurant)
    {
        Restaurant saved = null;
        try
        {
            saved = restaurantRepository.save(restaurant);
        }
        catch(Exception e)
        {
        }
        return saved;
    }

    public List<Restaurant> getRestaurants()
    {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }

    public Restaurant updateRestaurant(Restaurant restaurant)
    {
        Restaurant saved = restaurantRepository.save(restaurant);
        return saved;
    }
}
