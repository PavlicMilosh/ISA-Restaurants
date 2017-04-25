package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Milos on 13-Apr-17.
 */
@Service
public class RestaurantServiceImpl implements RestaurantService
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private TableRepository tableRepository;

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

    public Restaurant addManager(Restaurant restaurant, RestaurantManager restaurantManager)
    {
        Restaurant saved = restaurantRepository.save(restaurant);
        userRepository.save(restaurantManager);
        return saved;
    }

    public Restaurant updateRestaurant(Restaurant restaurant)
    {
        Restaurant old = getRestaurant(restaurant.getId());
        restaurantRepository.save(restaurant);
        for(Dish d : restaurant.getDishes())
            dishRepository.save(d);
        for(Drink d : restaurant.getDrinks())
            drinkRepository.save(d);
        for(RestaurantManager rm : restaurant.getManagers())
            userRepository.save(rm);
        for(RestaurantTable t : restaurant.getTables())
            tableRepository.save(t);

        for(Dish d : old.getDishes())
            if(!restaurant.getDishes().contains(d))
                dishRepository.delete(d);
        for(Drink d : old.getDrinks())
            if(!restaurant.getDrinks().contains(d))
                drinkRepository.delete(d);
        for(RestaurantManager rm : old.getManagers())
            if(!restaurant.getManagers().contains(rm))
                userRepository.delete(rm);
        for(RestaurantTable t : old.getTables())
            if(!restaurant.getTables().contains(t))
                tableRepository.delete(t);

        return restaurant;
    }

    @Transactional
    public Restaurant getRestaurant(Long id)
    {
        Restaurant r = restaurantRepository.findOne(id);
        r.getManagers();
        r.getDrinks();
        r.getDishes();
        r.getTables();
        return r;
    }
}
