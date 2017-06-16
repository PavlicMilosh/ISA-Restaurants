package com.isa.restaurant.services;
import com.isa.restaurant.domain.DTO.RegionDTO;
import com.isa.restaurant.domain.DTO.RestaurantDTO;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.RestaurantTableDTO;
import com.isa.restaurant.domain.DTO.UserDTO;

import java.util.List;

/**
 * Created by Milos on 15-Apr-17.
 */
public interface RestaurantService
{
    Restaurant addRestaurant(Restaurant restaurant);

    List<Restaurant> getRestaurants();

    List<RestaurantDTO> getRestaurants(Long guestId);

    Restaurant updateRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Long id);

    Restaurant getRestaurant(String name);

    Restaurant getByManagerId(Long managerId);

    UserDTO addRestaurantManager(RestaurantManager restaurantManager, Long restaurantId);

    UserDTO addWaiter(Waiter waiter, Long managerId);

    UserDTO addBartender(Bartender bartender, Long managerId);

    UserDTO addCook(Cook cook, Long managerId);

    List<UserDTO> getWorkersByRMId(Long managerId);

    List<RestaurantDTO> searchRestaurantsByNameAndDescription(String searchText);

    DishType addDishType(DishType dishType);

    List<RegionDTO> getRegions(Long restaurantId);

    List<RegionDTO> getRegionsByRMId(Long managerId);

    Integer getMedianMark(Long guestId);

    List<RestaurantTableDTO> getTables(Long restaurantId);
}
