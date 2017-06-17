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

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private TableRepository tableRepository;


    private Long id;

    @Before
    public void setUp()
    {
        Restaurant r1=restaurantRepository.save(new Restaurant("restoranOrder","desc"));

        DishType dishType = new DishType(r1,"salate");
        Waiter user = new Waiter("billRepositoryUser4@gmail.com", "123321", "billUser4", "billUser4");
        userRepository.save(user);


        RestaurantTable table=tableRepository.save(new RestaurantTable());
        Drink d1=drinkRepository.save(new Drink("Coca Cola 2","Gazirano pice",150.0, r1));
        Drink d2=drinkRepository.save(new Drink("Pepsi 2","Gazirano pice",150.0, r1));

        Dish di1=dishRepository.save(new Dish("dish1 2","desc",450.0,r1));
        Dish di2=dishRepository.save(new Dish("dish2 2","desc",600.0,r1));

        OrderItem orderItem1 = orderItemRepository.save(new OrderItem(d1,2));
        OrderItem orderItem2 = orderItemRepository.save(new OrderItem(d2,2));
        OrderItem orderItem3 = orderItemRepository.save(new OrderItem(di1,2));
        OrderItem orderItem4 = orderItemRepository.save(new OrderItem(di2,2));

        HashSet<OrderItem> orderItems=new HashSet<OrderItem>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);
        orderItems.add(orderItem4);

        Order order=new Order(user,orderItems,table);
        orderRepository.save(order);
        id=order.getId();
    }

    @Test
    public void testSave()
    {
        Restaurant r1=restaurantRepository.save(new Restaurant("restoranOrder1","desc"));
        DishType dishType = new DishType(r1,"salate");
        Waiter user = new Waiter("billRepositoryUser4@gmail.com", "123321", "billUser4", "billUser4");
        userRepository.save(user);
        RestaurantTable table=tableRepository.save(new RestaurantTable());
        Drink d1=drinkRepository.save(new Drink("Coca Cola 3","Gazirano pice",150.0, r1));
        Drink d2=drinkRepository.save(new Drink("Pepsi 3","Gazirano pice",150.0, r1));

        Dish di1=dishRepository.save(new Dish("dish1 3","desc",450.0,r1));
        Dish di2=dishRepository.save(new Dish("dish2 3","desc",600.0,r1));

        OrderItem orderItem1 = orderItemRepository.save(new OrderItem(d1,2));
        OrderItem orderItem2 = orderItemRepository.save(new OrderItem(d2,2));
        OrderItem orderItem3 = orderItemRepository.save(new OrderItem(di1,2));
        OrderItem orderItem4 = orderItemRepository.save(new OrderItem(di2,2));

        HashSet<OrderItem> orderItems=new HashSet<OrderItem>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);
        orderItems.add(orderItem4);

        Order order=new Order(user,orderItems,table);
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
