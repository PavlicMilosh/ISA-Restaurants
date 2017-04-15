package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Restaurant;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
