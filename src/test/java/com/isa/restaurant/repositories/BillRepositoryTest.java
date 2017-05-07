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
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

/**
 * Created by djuro on 5/4/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BillRepositoryTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private DishRepository dishRepository;

    private Long id;

    @Before
    public void setUp()
    {
        Guest user = new Guest("billRepositoryUser@gmail.com", "123321", "billUser1", "billUser1");
        userRepository.save(user);
        Bill bill=new Bill(user);
        billRepository.save(bill);
        id=bill.getId();
    }

    @Test
    public void testSave()
    {
        Guest user = new Guest("billRepositoryUser1@gmail.com", "123321", "billUser2", "billUser2");
        userRepository.save(user);

        Bill bill= new Bill(user);
        Bill saved=billRepository.save(bill);
        Assert.assertEquals(bill, saved);
    }

    @Test
    public void testSaveBillWithOrder()
    {
        Guest user = new Guest("billRepositoryUser3@gmail.com", "123321", "billUser3", "billUser3");
        userRepository.save(user);
        Bill bill= new Bill(user);

        Restaurant r1=restaurantRepository.save(new Restaurant("restoranBill1","desc"));
        Drink d1=drinkRepository.save(new Drink("Coca Cola 1","Gazirano pice",150L, r1));
        Drink d2=drinkRepository.save(new Drink("Pepsi 1","Gazirano pice",150L, r1));

        Dish di1=dishRepository.save(new Dish("dish1 1","desc",450L,r1));
        Dish di2=dishRepository.save(new Dish("dish2 1","desc",600L,r1));

        HashSet<Drink> drinks = new HashSet<Drink>();
        drinks.add(d1);
        drinks.add(d2);

        HashSet<Dish> dishes = new HashSet<Dish>();
        dishes.add(di1);
        dishes.add(di2);

        Order order=orderRepository.save(new Order(user,drinks,dishes));

        bill.addOrder(order);
        Bill saved=billRepository.save(bill);
        Assert.assertEquals(bill, saved);
    }


    @After
    public void tearDown()
    {
        Bill bill = billRepository.findById(id);

        try
        {
            billRepository.delete(bill.getId());
        }
        catch(NullPointerException e){}
    }
}
