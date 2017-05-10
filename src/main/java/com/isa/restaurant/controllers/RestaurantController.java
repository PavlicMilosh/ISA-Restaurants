package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.services.RestaurantService;
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
    @Autowired
    private RestaurantService restaurantService;

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
        return new ResponseEntity(restaurants, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody Restaurant restaurant)
    {
        Restaurant updated = restaurantService.updateRestaurant(restaurant);
        return new ResponseEntity(updated, HttpStatus.OK);
    }

    @RequestMapping(value = "/{restaurantId}/addRM", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerRestaurantManager(@PathVariable Long restaurantId, @RequestBody RestaurantManager restaurantManager)
    {
        UserDTO saved = restaurantService.addRestaurantManager(restaurantManager, restaurantId);
        if(saved == null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{restaurantId}/addWaiter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> addWaiter(@PathVariable Long restaurantId, @RequestBody Waiter waiter)
    {
        UserDTO saved = restaurantService.addWaiter(waiter, restaurantId);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{restaurantId}/addBartender", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> addBartender(@PathVariable Long restaurantId, @RequestBody Bartender bartender)
    {
        UserDTO saved = restaurantService.addBartender(bartender, restaurantId);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{restaurantId}/addCook", method = RequestMethod.POST)
    public ResponseEntity<UserDTO> addCook(@PathVariable Long restaurantId, @RequestBody Cook cook)
    {
        UserDTO saved = restaurantService.addCook(cook, restaurantId);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

}
