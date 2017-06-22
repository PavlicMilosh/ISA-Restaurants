package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.repositories.OfferRepository;
import com.isa.restaurant.repositories.ShoppingRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
    private OfferRepository offerRepository;

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

    @Override
    public List<ShoppingList> getShoppingLists()
    {
        List<ShoppingList> lists = shoppingRepository.findAll();
        return lists;
    }

    @Override
    public List<ShoppingList> getShoppingLists(Long rmId)
    {
        List<ShoppingList> lists = shoppingRepository.findAll();
        Restaurant r = ((RestaurantManager) userRepository.findOne(rmId)).getRestaurant();
        List<ShoppingList> ret = new ArrayList<>();
        for(ShoppingList sl : lists)
        {
            if(sl.getRestaurant().getId() == r.getId())
            {
                ret.add(sl);
            }
        }
        return ret;
    }

    @Override
    @Transactional(rollbackFor = OptimisticLockingFailureException.class)
    public Offer sendOffer(Offer o, Long providerId, Long shoppingListId) throws OptimisticLockingFailureException
    {
        Calendar c = Calendar.getInstance();
        ShoppingList sl = shoppingRepository.findOne(shoppingListId);
        if (new Date(c.getTime().getTime()).after(sl.getDeadline()))
            return null;

        Provider p = (Provider) userRepository.findOne(providerId);
        o.setProvider(p);
        o.setShoppingList(sl);

        Offer ret = offerRepository.save(o);
        return ret;
    }

    @Override
    @Transactional(rollbackFor = OptimisticLockingFailureException.class)
    public Boolean acceptOffer(Offer o, Long shoppingListId) throws OptimisticLockingFailureException
    {
        Offer saved = offerRepository.save(o);
        ShoppingList sl = shoppingRepository.findOne(shoppingListId);
        sl.setAcceptedOffer(saved);
        for(Offer oo : sl.getOffers())
        {
            oo.setAccepted(false);
            offerRepository.save(oo);
        }
        saved.setAccepted(true);
        shoppingRepository.save(sl);
        return true;
    }
}
