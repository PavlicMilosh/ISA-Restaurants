package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Restaurant;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Milos on 25-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DishRepositoryTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private MockMvc mvc;

    private Long g1;

    @Before
    public void setUp()
    {
        Restaurant restaurant=new Restaurant("restoran0", "desc");
        restaurantRepository.save(restaurant);
        Dish jelo=new Dish("Jelo1","Moje jelo",200.0, restaurant);
        dishRepository.save(jelo);
        g1=jelo.getId();
    }

    @Test
    public void testSave()
    {
        Restaurant restaurant=new Restaurant("restoran1","moj restoran");
        restaurantRepository.save(restaurant);

        Dish dish= new Dish("Jelo2","Moje jelo2",300.0,restaurant);
        Dish saved=dishRepository.save(dish);
        Assert.assertEquals(dish, saved);
    }

    @Test
    public void testFindById()
{
    Restaurant restaurant=new Restaurant("restoran2","moj restoran");
    restaurantRepository.save(restaurant);

    Dish dish= new Dish("Jelo3","Moje jelo3",300.0,restaurant);
    Dish saved=dishRepository.save(dish);
    Dish getDish=dishRepository.findById(saved.getId());
    Assert.assertEquals(getDish, saved);
}


    @After
    public void tearDown()
    {
        Dish jelo = dishRepository.findById(g1);

        try
        {
            dishRepository.delete(jelo.getId());
        }
        catch(NullPointerException e){}
    }

}
