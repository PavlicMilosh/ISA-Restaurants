package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.ShoppingItem;
import com.isa.restaurant.domain.ShoppingList;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Milos on 05-Jun-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ShoppingRepositoryTest
{
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    ShoppingRepository shoppingRepository;

    @Before
    public void setUp()
    {
        Restaurant rr = new Restaurant("rr", "rr");
        restaurantRepository.save(rr);
    }

    @Test
    public void testSave()
    {
        Restaurant r = restaurantRepository.findByName("rr");
        ShoppingList shoppingList = new ShoppingList();
        shoppingList.setRestaurant(r);
        shoppingList.setDeadline(new Date(2017, 6, 15));
        shoppingList.addItem(new ShoppingItem("svinja", "5 kom"));
        shoppingList.addItem(new ShoppingItem("krilca", "5 kom"));
        shoppingList.addItem(new ShoppingItem("mleko", "10 l"));

        ShoppingList saved = shoppingRepository.save(shoppingList);
        Assert.assertEquals(r.getId(), saved.getRestaurant().getId());
        Assert.assertEquals(shoppingList.getDeadline(), saved.getDeadline());
        ArrayList<ShoppingItem> expectedItems = new ArrayList<>(shoppingList.getItems());
        ArrayList<ShoppingItem> savedItems = new ArrayList<>(saved.getItems());
        for(int i = 0; i < expectedItems.size(); i++)
        {
            Assert.assertEquals(expectedItems.get(i).getName(), savedItems.get(i).getName());
            Assert.assertEquals(expectedItems.get(i).getAmount(), savedItems.get(i).getAmount());
        }
    }

    @Test
    public void testFindOne()
    {

        ShoppingList found = shoppingRepository.findOne(0l);
        Assert.assertEquals(0l, found.getId().longValue());
        Assert.assertEquals("Sarma", found.getName());
        Assert.assertNull(found.getAcceptedOffer());
        Assert.assertEquals(0l, found.getRestaurant().getId().longValue());

        ShoppingList notFound = shoppingRepository.findOne(100l);
        Assert.assertNull(notFound);

    }

    public void testFindAll()
    {
        List<ShoppingList> lists = shoppingRepository.findAll();
        Assert.assertEquals(2, lists.size());
    }

    @After
    public void tearDown()
    {

    }
}
