package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Dish;
import com.isa.restaurant.domain.Drink;
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
public class DrinkRepositoryTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private MockMvc mvc;

    private Long g1;

    @Before
    public void setUp()
    {
        Restaurant restaurant=new Restaurant("restoran2", "desc");
        restaurantRepository.save(restaurant);
        Drink drink=new Drink("Pice1","Moje pice",200.0, restaurant);
        drinkRepository.save(drink);
        g1=restaurant.getId();
    }

    @Test
    public void testSave()
    {
        Restaurant restaurant=new Restaurant("restoran3","moj restoran");
        restaurantRepository.save(restaurant);

        Drink drink= new Drink("Drink3","Moje pice2",300.0,restaurant);
        Drink saved=drinkRepository.save(drink);
        Assert.assertEquals(drink, saved);
    }

    @Test
    public void testFindById()
    {
        Restaurant restaurant=new Restaurant("restoran4","moj restoran");
        restaurantRepository.save(restaurant);

        Drink drink= new Drink("Drink4","Moje pice3",300.0,restaurant);
        Drink saved=drinkRepository.save(drink);
        Drink getDrink=drinkRepository.findById(saved.getId());

        Assert.assertEquals(getDrink, saved);
    }

    @After
    public void tearDown()
    {
        Drink drink = drinkRepository.findById(g1);

        try
        {
            drinkRepository.delete(drink.getId());
        }
        catch(NullPointerException e){}
    }
}
