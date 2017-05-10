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

/**
 * Created by djuro on 5/4/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderRepositoryTest
{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private Long id;

    @Before
    public void setUp()
    {
        Guest user = new Guest("billRepositoryUser4@gmail.com", "123321", "billUser4", "billUser4");
        userRepository.save(user);

        Restaurant r1=restaurantRepository.save(new Restaurant("restoranOrder","desc"));
        Drink d1=drinkRepository.save(new Drink("Coca Cola 2","Gazirano pice",150L, r1));
        Drink d2=drinkRepository.save(new Drink("Pepsi 2","Gazirano pice",150L, r1));

        Dish di1=dishRepository.save(new Dish("dish1 2","desc",450L,r1));
        Dish di2=dishRepository.save(new Dish("dish2 2","desc",600L,r1));

        HashSet<Drink> drinks = new HashSet<Drink>();
        drinks.add(d1);
        drinks.add(d2);

        HashSet<Dish> dishes = new HashSet<Dish>();
        dishes.add(di1);
        dishes.add(di2);

        Order order=new Order(user,drinks,dishes);
        orderRepository.save(order);
        id=order.getId();
    }

    @Test
    public void testSave()
    {
        Guest user = new Guest("billRepositoryUser5@gmail.com", "123321", "billUser5", "billUser5");
        userRepository.save(user);

        Restaurant r1=restaurantRepository.save(new Restaurant("restoranOrder1","desc"));
        Drink d1=drinkRepository.save(new Drink("Coca Cola 3","Gazirano pice",150L, r1));
        Drink d2=drinkRepository.save(new Drink("Pepsi 3","Gazirano pice",150L, r1));

        Dish di1=dishRepository.save(new Dish("dish1 3","desc",450L,r1));
        Dish di2=dishRepository.save(new Dish("dish2 3","desc",600L,r1));

        HashSet<Drink> drinks = new HashSet<Drink>();
        drinks.add(d1);
        drinks.add(d2);

        HashSet<Dish> dishes = new HashSet<Dish>();
        dishes.add(di1);
        dishes.add(di2);

        Order order=new Order(user,drinks,dishes);
        Order saved=orderRepository.save(order);
        Assert.assertEquals(order, saved);
    }


    @After
    public void tearDown()
    {
        Order order = orderRepository.findById(id);

        try
        {
            orderRepository.delete(order.getId());
        }
        catch(NullPointerException e){}
    }
}
