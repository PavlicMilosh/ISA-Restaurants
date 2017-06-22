package com.isa.restaurant.services;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.domain.*;

import java.sql.Date;
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

    Report getReport(Long restaurantId, Date startDate);

    List<WaiterMarkReport> getWaiterMarkReport(Long restaurantId);

    List<DishMarkReport> getDishMarkReport(Long restaurantId);

    List<CookMarkReport> getCookMarkReport(Long restaurantId);

    List<ReportData> getVisitsReport(Long restaurantId, Date date);

    List<ReportData> getWaiterIncomeReport(Long restaurantId, Long waiterId, Date date);

    List<ReportData> getIncomeReport(Long restaurantId, Date date);

    List<DishType> getDishTypes(Long managerId);

    List<RestaurantTableDTO> getRestaurantTables(Long waiterId);
}
