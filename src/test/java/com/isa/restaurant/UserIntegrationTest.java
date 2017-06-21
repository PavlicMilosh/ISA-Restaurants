package com.isa.restaurant;

import com.isa.restaurant.domain.DishType;
import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.Waiter;
import com.isa.restaurant.repositories.RestaurantRepository;
import com.isa.restaurant.repositories.UserRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Milos on 11-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private MockMvc mvc;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        Restaurant r = restaurantRepository.save(new Restaurant("MyFirstRestaurant", "My first restaurant"));
        DishType dishType = new DishType(r,"salate");
        Waiter w = new Waiter("waiter", "waiter", "waiter", "waiter");
        w.setRestaurant(r);
        userRepository.save(w);
    }

    @Test
    public void testAddingSM() throws Exception
    {
        this.mvc.perform(post("/users/register/sysManager")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"email\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));

        this.mvc.perform(post("/users/register/sysManager")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void testAddingSchedule() throws Exception
    {
        Waiter w = (Waiter) userRepository.findByEmail("waiter");
        this.mvc.perform(post("/users/" + w.getId() + "/addSchedule")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"startTime\":\"15:00\", \"endTime\":\"22:00\", \"day\":1}]"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testAddingOthers() throws Exception
    {

        /*this.mvc.perform(post("/users/register/barman")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"pera\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"username\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));

        this.mvc.perform(post("/users/update/barman")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"email\":\"zika\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(content().json("{\"username\":\"zika\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));

        this.mvc.perform(post("/users/update/cook")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"email\":\"sava2\", \"password\":\"sava2\", \"firstName\":\"sava2\", \"lastName\":\"sava2\"}"))
                .andExpect(content().json("{\"username\":\"sava2\", \"firstName\":\"sava2\", \"lastName\":\"sava2\"}"));

        this.mvc.perform(post("/users/update/bartender")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\"1\", \"email\":\"zika\", \"password\":\"pera\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"))
                .andExpect(content().json("{\"username\":\"zika\", \"firstName\":\"Pera\", \"lastName\":\"peric\"}"));
        */
    }

}
