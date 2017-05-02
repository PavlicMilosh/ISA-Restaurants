package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.RestaurantManager;
import com.isa.restaurant.services.RestaurantService;
import com.sun.media.jfxmedia.logging.Logger;
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
}
