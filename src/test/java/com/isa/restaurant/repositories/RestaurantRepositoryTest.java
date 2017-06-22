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

    }

    @Test
    public void testSave()
    {
        Restaurant restaurant = new Restaurant("MyFirstRestaurant", "My first restaurant");
        Restaurant saved = restaurantRepository.save(restaurant);
        Assert.assertEquals(restaurant, saved);

        Restaurant r1 = new Restaurant(saved);
        r1.addManager(new RestaurantManager("email", "pass", "name", "lastName", r1));
        Restaurant r2 = restaurantRepository.save(r1);
        Assert.assertEquals(r1, r2);
    }

    @Test
    public void testFindByName()
    {
        Restaurant found = restaurantRepository.findByName("r");
        Assert.assertEquals(0l, found.getId().longValue());
    }

    @After
    public void tearDown()
    {
    }
}
