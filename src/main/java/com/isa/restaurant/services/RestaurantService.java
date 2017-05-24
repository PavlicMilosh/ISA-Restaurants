package com.isa.restaurant.services;
import com.isa.restaurant.domain.DTO.RestaurantDTO;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.UserDTO;

import java.util.List;

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

    UserDTO addWaiter(Waiter waiter, Long restaurantId);

    UserDTO addBartender(Bartender bartender, Long restaurantId);

    UserDTO addCook(Cook cook, Long restaurantId);

    List<UserDTO> getWorkersByRMId(Long managerId);

    List<RestaurantDTO> searchRestaurantsByNameAndDescription(String searchText);
}
