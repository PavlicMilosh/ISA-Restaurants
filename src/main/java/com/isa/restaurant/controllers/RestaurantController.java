package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Milos on 14-Apr-17.
 */
@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController
{
    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> addRestaurant(@RequestBody Restaurant restaurant)
    {
        Restaurant saved = restaurantService.addRestaurant(restaurant);
        if(saved == null)
        {
            return new ResponseEntity<Restaurant>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<Restaurant>(saved, HttpStatus.CREATED);
    }
}
