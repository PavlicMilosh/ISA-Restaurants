package com.isa.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.repositories.RestaurantRepository;
import com.isa.restaurant.services.RestaurantService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Milos on 14-Apr-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class RestaurantIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    private MockMvc mvc;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        Restaurant r = new Restaurant("R1", "desc");
        restaurantRepository.save(r);
    }

    @Test
    public void testAddingRestaurant() throws Exception
    {
        ObjectMapper om = new ObjectMapper();

        this.mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\" : \"My restaurant2\", \"description\" : \"Some text for description\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{\"name\" : \"My restaurant2\", \"description\" : \"Some text for description\"}"));

        this.mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\"name\": \"R1\",\n" +
                        "\"description\": \"Some text for description\",\n" +
                        "\"dishes\": [],\n" +
                        "\"drinks\": [],\n" +
                        "\"managers\": [],\n" +
                        "\"tables\": []\n" +
                        "}"))
                .andExpect(status().isConflict());
    }

    @Test
    public void testUpdate() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        Restaurant r = restaurantService.getRestaurant("R1");
        Restaurant r1 = new Restaurant(r);
        r1.addDish(new Dish("dish1", "desc1", 11.0, null));
        r1.addDrink(new Drink("drink1", "descd", 11.0, null));
        Region region = new Region("bascu", null, new HashSet<>());
        r1.addRegion(region);
        r1.addTable(new RestaurantTable(new Double(1), new Double(1), new Double(1), null, new HashSet<>()));
        String s = om.writeValueAsString(r1);
        this.mvc.perform(put("/restaurants/" + r.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk())
                .andExpect(content().json(s));
    }

    @Test
    public void testAddingWorkers() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        Restaurant r = restaurantService.getRestaurant("R1");
        Bartender b = new Bartender("b", "b", "b", "b");
        UserDTO bb = new UserDTO(b);
        bb.setId(1l);
        Cook c = new Cook("c", "c", "c", "c");
        UserDTO cc = new UserDTO(c);
        cc.setId(2l);
        DishType dishType = new DishType(r,"salate");
        Waiter w = new Waiter("w", "w", "w", "w");
        UserDTO ww = new UserDTO(w);
        ww.setId(3l);
        this.mvc.perform(post("/restaurants/" + r.getId() + "/addBartender")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(b)))
                .andExpect(status().isCreated())
                .andExpect((content().json(om.writeValueAsString(bb))));

        this.mvc.perform(post("/restaurants/" + r.getId() + "/addCook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(c)))
                .andExpect(status().isCreated())
                .andExpect((content().json(om.writeValueAsString(cc))));

        this.mvc.perform(post("/restaurants/" + r.getId() + "/addWaiter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(w)))
                .andExpect(status().isCreated())
                .andExpect((content().json(om.writeValueAsString(ww))));

    }

    @After
    public void tearDown()
    {
        Restaurant r1 = restaurantRepository.findByName("My Restaurant2");
        if(r1 != null)
            restaurantRepository.delete(r1.getId());

        Restaurant r2 = restaurantRepository.findByName("R1");
        if(r2 != null)
            restaurantRepository.delete(r2.getId());
    }
}
