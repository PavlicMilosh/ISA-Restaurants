package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.RestaurantManager;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.services.RestaurantService;
import com.isa.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Milos on 14-Apr-17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/restaurants")
public class RestaurantController
{
    private final RestaurantService restaurantService;
    private final UserService userService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService,
                                UserService userService)
    {
        this.restaurantService = restaurantService;
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant)
    {
        Restaurant saved = restaurantService.addRestaurant(restaurant);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/findByManagerId/{managerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> getByManager(@PathVariable("managerId") Long managerId)
    {
        Restaurant rest = this.restaurantService.getByManagerId(managerId);

        if(rest == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(rest, HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Restaurant>> getAllRestaurants()
    {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant)
    {
        Restaurant updated = restaurantService.updateRestaurant(restaurant);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }


    @RequestMapping(value = "/{restaurantId}/addRM", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerRestaurantManager(@PathVariable Long restaurantId, @RequestBody RestaurantManager restaurantManager)
    {
        UserDTO saved = restaurantService.addRestaurantManager(restaurantManager, restaurantId);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{managerId}/addWaiter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addWaiter(@PathVariable Long managerId, @RequestBody Waiter waiter)
    {
        UserDTO saved = restaurantService.addWaiter(waiter, managerId);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{managerId}/addBartender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addBartender(@PathVariable Long managerId, @RequestBody Bartender bartender)
    {
        UserDTO saved = restaurantService.addBartender(bartender, managerId);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{managerId}/addCook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addCook(@PathVariable Long managerId, @RequestBody Cook cook)
    {
        UserDTO saved = restaurantService.addCook(cook, managerId);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "getWorkersByRMId/{managerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getWorkersByRMId(@PathVariable Long managerId)
    {
        List<UserDTO> workers = restaurantService.getWorkersByRMId(managerId);
        if(workers == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(workers, HttpStatus.OK);
    }


    @RequestMapping(value = "/searchRestaurants", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantDTO>> searchRestaurants(@RequestBody String searchParams)
    {
        List<RestaurantDTO> ret = restaurantService.searchRestaurantsByNameAndDescription(searchParams);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }


    @RequestMapping(value = "/addDishType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishType> addDishType(@RequestBody DishType dishType)
    {
        DishType saved = restaurantService.addDishType(dishType);
        if(saved == null)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{managerId}/dishTypes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DishType>> getDishTypes(@PathVariable Long managerId)
    {
        List<DishType> dishTypes = restaurantService.getDishTypes(managerId);
        return new ResponseEntity<>(dishTypes, HttpStatus.OK);
    }


    @RequestMapping(value = "/{restaurantId}/getRegions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegionDTO>> getRegions(@PathVariable Long restaurantId)
    {
        List<RegionDTO> regions = this.restaurantService.getRegions(restaurantId);
        if (regions == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @RequestMapping(value = "/RM/{managerId}/getRegions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RegionDTO>> getRegionsByRMId(@PathVariable Long managerId)
    {
        List<RegionDTO> regions = this.restaurantService.getRegionsByRMId(managerId);
        if (regions == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }


    @RequestMapping(value = "/{restaurantId}/getTables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTableDTO>> getTables(@PathVariable Long restaurantId)
    {
        List<RestaurantTableDTO> tables = this.restaurantService.getTables(restaurantId);
        if (tables == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    @RequestMapping(value = "/waiter/{waiterId}/getRestaurantsTables", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTableDTO>> getRestaurantsTables(@PathVariable Long waiterId)
    {
        List<RestaurantTableDTO> tables = this.restaurantService.getRestaurantTables(waiterId);
        if (tables == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(tables, HttpStatus.OK);
    }

    //Reports

    @RequestMapping(value = "/{restaurantId}/markReport/waiters", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WaiterMarkReport>> getWaitersMarkReport(@PathVariable Long restaurantId)
    {
        List<WaiterMarkReport> report = restaurantService.getWaiterMarkReport(restaurantId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/{restaurantId}/markReport/dishes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DishMarkReport>> getDishesMarkReport(@PathVariable Long restaurantId)
    {
        List<DishMarkReport> report = restaurantService.getDishMarkReport(restaurantId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/{restaurantId}/markReport/cooks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CookMarkReport>> getCooksMarkReport(@PathVariable Long restaurantId)
    {
        List<CookMarkReport> report = restaurantService.getCookMarkReport(restaurantId);
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/{restaurantId}/incomeReport", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReportData>> getIncomeReport(@PathVariable Long restaurantId, @RequestBody DateDTO startDate)
    {
        List<ReportData> report = restaurantService.getIncomeReport(restaurantId, startDate.getStartDate());
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/{restaurantId}/incomeReport/waiters/{waiterId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReportData>> getWaitersIncomeReport(@PathVariable Long restaurantId, @PathVariable Long waiterId, @RequestBody DateDTO startDate)
    {
        List<ReportData> report = restaurantService.getWaiterIncomeReport(restaurantId, waiterId, startDate.getStartDate());
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    @RequestMapping(value = "/{restaurantId}/visitsReport", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReportData>> getVisitsReport(@PathVariable Long restaurantId, @RequestBody DateDTO startDate)
    {
        List<ReportData> report = restaurantService.getVisitsReport(restaurantId, startDate.getStartDate());
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

}
