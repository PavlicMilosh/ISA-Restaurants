package com.isa.restaurant;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

/**
 * Created by djuro on 4/24/2017.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

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


    private MockMvc mvc;

    private Long orderId;

    private Order order;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        Restaurant r1=restaurantRepository.save(new Restaurant("restoran","desc"));

        DishType dishType = new DishType(r1,"salate");
        Waiter g1 = new Waiter("billRepositoryUser4@gmail.com", "123321", "billUser4", "billUser4");
        userRepository.save(g1);

        RestaurantTable table=tableRepository.save(new RestaurantTable());
        Drink d1=drinkRepository.save(new Drink("Coca Cola","Gazirano pice",150L, r1));
        Drink d2=drinkRepository.save(new Drink("Pepsi","Gazirano pice",150L, r1));

        Dish di1=dishRepository.save(new Dish("dish1","desc",450L,r1));
        Dish di2=dishRepository.save(new Dish("dish2","desc",600L,r1));

        OrderItem orderItem1 = orderItemRepository.save(new OrderItem(d1,2));
        OrderItem orderItem2 = orderItemRepository.save(new OrderItem(d2,2));
        OrderItem orderItem3 = orderItemRepository.save(new OrderItem(di1,2));
        OrderItem orderItem4 = orderItemRepository.save(new OrderItem(di2,2));

        HashSet<OrderItem> orderItems=new HashSet<OrderItem>();
        orderItems.add(orderItem1);
        orderItems.add(orderItem2);
        orderItems.add(orderItem3);
        orderItems.add(orderItem4);

        order=orderRepository.save(new Order(g1, orderItems, table));

        orderId=order.getId();
    }

    @Test
    public void testFinishingOrder() throws Exception
    {

        this.mvc.perform(
                put("/order/"+orderId+"/finish"))
                .andExpect(status().isOk());

    }
    
}
