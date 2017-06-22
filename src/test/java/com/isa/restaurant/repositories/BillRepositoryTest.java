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

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private TableRepository tableRepository;

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
        Restaurant r1=restaurantRepository.save(new Restaurant("restoranBill1hh","desc"));

        DishType dishType = new DishType(r1,"salatee");
        Waiter user = new Waiter("billRepositoryUser4p@gmail.com", "123321", "billUser4", "billUser4");
        userRepository.save(user);

        Bill bill= new Bill(user);

        RestaurantTable table=tableRepository.save(new RestaurantTable());
        Drink d1=drinkRepository.save(new Drink("Coca Cola 1j","Gazirano pice",150.0, r1));
        Drink d2=drinkRepository.save(new Drink("Pepsi 1j","Gazirano pice",150.0, r1));

        Dish di1=dishRepository.save(new Dish("dish1 1j","desc",450.0,r1));
        Dish di2=dishRepository.save(new Dish("dish2 1j","desc",600.0,r1));

        OrderItem orderItem1 = orderItemRepository.save(new OrderItem(d1,2));
        OrderItem orderItem2 = orderItemRepository.save(new OrderItem(d2,2));
        OrderItem orderItem3 = orderItemRepository.save(new OrderItem(di1,2));
        OrderItem orderItem4 = orderItemRepository.save(new OrderItem(di2,2));

        HashSet<OrderItem> orderItems=new HashSet<OrderItem>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);
        orderItems.add(orderItem4);

        Order order=orderRepository.save(new Order(user,orderItems,table));

        bill.addOrder(order);
        Bill saved=billRepository.save(bill);
        Assert.assertEquals(bill, saved);
    }

    @Test
    public void findById()
    {
        Restaurant r1=restaurantRepository.save(new Restaurant("restoranBill2","desc"));

        DishType dishType = new DishType(r1,"salate1");
        Waiter user = new Waiter("billRepositoryUser5@gmail.com", "123321", "billUser4", "billUser4");
        userRepository.save(user);
        Bill bill= new Bill(user);

        Bill saved=billRepository.save(bill);

        Bill getBill=billRepository.findById(saved.getId());

        Assert.assertEquals(saved, getBill);

    }


    @Test
    public void findByUser()
    {
        Restaurant r1=restaurantRepository.save(new Restaurant("restoranBill3","desc"));

        DishType dishType = new DishType(r1,"salate2");
        Waiter user = new Waiter("billRepositoryUser6@gmail.com", "123321", "billUser4", "billUser4");
        userRepository.save(user);
        Bill bill= new Bill(user);

        Bill saved=billRepository.save(bill);

        Bill getBill=billRepository.findByUser(user);

        Assert.assertEquals(saved, getBill);

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
