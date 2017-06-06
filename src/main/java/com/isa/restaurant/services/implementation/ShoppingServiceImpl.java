package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.RestaurantManager;
import com.isa.restaurant.domain.ShoppingList;
import com.isa.restaurant.repositories.ShoppingRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Milos on 04-Jun-17.
 */
@Service
@Transactional
public class ShoppingServiceImpl implements ShoppingService
{
    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean addShoppinglist(ShoppingList shoppingList, Long rmId)
    {
        RestaurantManager rm = (RestaurantManager) userRepository.findOne(rmId);
        try
        {
            Restaurant r = rm.getRestaurant();
            shoppingList.setRestaurant(r);
            shoppingRepository.save(shoppingList);
            return true;
        }
        catch (NullPointerException e)
        {
            return false;
        }
    }
}
