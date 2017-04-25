package com.isa.restaurant;

import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.repositories.RestaurantRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Milos on 14-Apr-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestaurantIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RestaurantRepository restaurantRepository;

    private MockMvc mvc;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        restaurantRepository.save(new Restaurant("R1", "desc"));
    }

    @Test
    public void testAddingRestaurant() throws Exception
    {
        this.mvc.perform(post("/restaurants/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"My restaurant2\", \"description\" : \"Some text for description\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"name\" : \"My restaurant2\", \"description\" : \"Some text for description\"}"));

        this.mvc.perform(post("/restaurants/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"R1\", \"description\":\"dfsfdf\"}"))
                .andExpect(status().isConflict());
    }

    public void testUpdate() throws Exception
    {

    }

    @After
    public void tearDown()
    {
        Restaurant r1 = restaurantRepository.findByName("My Restaurant2");
        restaurantRepository.delete(r1.getId());

        Restaurant r2 = restaurantRepository.findByName("R1");
        restaurantRepository.delete(r2.getId());
    }
}
