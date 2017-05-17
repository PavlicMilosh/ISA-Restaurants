package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.RestaurantDTO;
import com.isa.restaurant.domain.DTO.RestaurantTableDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.RestaurantManager;

import java.util.List;
import java.util.Set;

/**
 * Created by Milos on 15-Apr-17.
 */
public interface RestaurantService
{
    Restaurant addRestaurant(Restaurant restaurant);
    List<Restaurant> getRestaurants();
    Restaurant updateRestaurant(Restaurant restaurant);
    Restaurant getRestaurant(Long id);
    Restaurant getRestaurant(String name);
    Restaurant getByManagerId(Long managerId);
    UserDTO addRestaurantManager(RestaurantManager restaurantManager, Long restaurantId);
    List<RestaurantDTO> searchRestaurantsByNameAndDescription(String searchText);
}
