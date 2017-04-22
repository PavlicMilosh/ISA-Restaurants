package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Milos on 13-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RestaurantRepositoryTest
{
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Before
    public void setUp()
    {
        restaurantRepository.save(new Restaurant("r1", "desc"));
    }

    @Test
    public void testSave()
    {
        Restaurant restaurant = new Restaurant("MyFirstRestaurant", "My first restaurant");
        Restaurant saved = restaurantRepository.save(restaurant);
        Assert.assertEquals(restaurant, saved);

        Restaurant r1 = new Restaurant(saved.getId(), saved.getName(), saved.getDescription(), new HashSet<Dish>(), new HashSet<Drink>(), new HashSet<RestaurantManager>(), new HashSet<RestaurantTable>());
        r1.addManager(new RestaurantManager("email", "pass", "name", "lastName", r1));
        Restaurant r2 = restaurantRepository.save(r1);

        //TODO finish test (for updating)
    }

    @Test
    public void testFindByName()
    {
        Restaurant restaurant = new Restaurant("r1", "desc");
        Restaurant found = restaurantRepository.findByName("r1");
        Assert.assertEquals(restaurant, found);
    }

    @After
    public void tearDown()
    {
        Restaurant r1 = restaurantRepository.findByName("r1");
        Restaurant r2 = restaurantRepository.findByName("MyFirstRestaurant");
        try
        {
            restaurantRepository.delete(r1.getId());
        }
        catch(NullPointerException e){}
        try
        {

            restaurantRepository.delete(r2.getId());
        }
        catch(NullPointerException e) {}
    }
}
