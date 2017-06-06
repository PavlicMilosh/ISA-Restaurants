package com.isa.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.ShoppingItem;
import com.isa.restaurant.domain.ShoppingList;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Milos on 06-Jun-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ShoppingIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testAddingShoppingList() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        ShoppingList sl = new ShoppingList();
        sl.addItem(new ShoppingItem("item1", "amount1"));
        sl.addItem(new ShoppingItem("item2", "amount2"));
        String s = om.writeValueAsString(sl);
        this.mvc.perform(post("/shopping/-2/addShoppingList")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isCreated());
    }

    @After
    public void tearDown()
    {

    }
}
