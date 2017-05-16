package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
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

    @Override
    public List<Restaurant> getRestaurants()
    {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants;
    }


    @Override
    public Restaurant updateRestaurant(Restaurant restaurant)
    {
        for(Dish d : restaurant.getDishes())
            d.setRestaurant(restaurant);
        for(Drink d : restaurant.getDrinks())
            d.setRestaurant(restaurant);
        for(RestaurantManager rm : restaurant.getManagers())
            rm.setRestaurant(restaurant);
        for(RestaurantTable t : restaurant.getTables())
        {
            t.setRestaurant(restaurant);
            t.getRegion().addTable(t);
        }
        for(Region r : restaurant.getRegions())
            r.setRestaurant(restaurant);
        Restaurant retval = restaurantRepository.save(restaurant);
        return retval;
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

    @Transactional
    public Restaurant getRestaurant(String name)
    {
        Restaurant r = restaurantRepository.findByName(name);
        r.getManagers();
        r.getDrinks();
        r.getDishes();
        r.getTables();
        return r;
    }

    @Transactional
    public Restaurant getByManagerId(Long managerId)
    {
        RestaurantManager rm = (RestaurantManager) userRepository.findOne(managerId);
        try
        {
            return rm.getRestaurant();
        }catch(NullPointerException e)
        {
            return null;
        }
    }

    public UserDTO addRestaurantManager(RestaurantManager restaurantManager, Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        if(r == null)
            return null;
        restaurantManager.setRestaurant(r);
        userRepository.save(restaurantManager);
        return new UserDTO(restaurantManager);
    }

    @Override
    public UserDTO addWaiter(Waiter waiter, Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        waiter.setRestaurant(r);
        userRepository.save(waiter);
        return new UserDTO(waiter);
    }

    @Override
    public UserDTO addBartender(Bartender bartender, Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        bartender.setRestaurant(r);
        userRepository.save(bartender);
        return new UserDTO(bartender);
    }

    @Override
    public UserDTO addCook(Cook cook, Long restaurantId)
    {
        Restaurant r = restaurantRepository.findOne(restaurantId);
        cook.setRestaurant(r);
        userRepository.save(cook);
        return new UserDTO(cook);
    }

    @Override
    public List<UserDTO> getWorkersByRMId(Long managerId)
    {
        try
        {
            RestaurantManager rm = (RestaurantManager) userRepository.findOne(managerId);
            Restaurant r = rm.getRestaurant();
            List<UserDTO> retval = new ArrayList<>();
            for (Bartender b : r.getBartenders())
            {
                retval.add(new UserDTO(b));
            }
            for (Waiter w : r.getWaiters())
            {
                retval.add(new UserDTO(w));
            }
            for (Cook c : r.getCooks())
            {
                retval.add(new UserDTO(c));
            }
            return retval;
        }
        catch(NullPointerException e)
        {
            return null;
        }
    }
}
