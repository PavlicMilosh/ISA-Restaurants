package com.isa.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.repositories.OfferRepository;
import com.isa.restaurant.repositories.RestaurantRepository;
import com.isa.restaurant.repositories.ShoppingRepository;
import com.isa.restaurant.repositories.UserRepository;
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

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

    @Autowired
    private ShoppingRepository shoppingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OfferRepository offerRepository;

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

    @Test
    public void testSendingOffer() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        Offer o = new Offer();
        o.setAmount(1234l);
        String s = om.writeValueAsString(o);
        ShoppingList sl = shoppingRepository.findOne(0l);
        o.setShoppingList(sl);
        Provider p = (Provider) userRepository.findOne(-5l);
        o.setProvider(p);
        o.setId(1l);
        o.setVersion(0l);
        String expected = om.writeValueAsString(o);

        this.mvc.perform(put("/shopping/" + -5 + "/" + 0 + "/sendOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

        sl.setAcceptedOffer(o);
        shoppingRepository.save(sl);

        this.mvc.perform(put("/shopping/" + -5 + "/" + 0 + "/sendOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isConflict());

        sl.setDeadline(new Date(456));
        sl.setAcceptedOffer(null);
        shoppingRepository.save(sl);

        this.mvc.perform(put("/shopping/" + -5 + "/" + 0 + "/sendOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isConflict());
    }

    @Test
    public void testAcceptingOffer() throws Exception
    {
        ObjectMapper om = new ObjectMapper();

        Offer o = new Offer();
        o.setAmount(1234l);
        ShoppingList sl = shoppingRepository.findOne(0l);
        o.setShoppingList(sl);
        Provider p = (Provider) userRepository.findOne(-5l);
        o.setProvider(p);
        o.setId(1l);
        o.setVersion(1l);
        offerRepository.save(o);

        String s = om.writeValueAsString(o);

        this.mvc.perform(put("/shopping/" + -0 + "/acceptOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isOk());

        o.setVersion(0l);
        offerRepository.save(o);

        this.mvc.perform(put("/shopping/" + -0 + "/acceptOffer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(status().isConflict());

    }

    @Test
    public void testGettingLists() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        List<ShoppingList> sl = shoppingRepository.findAll();
        String expected = om.writeValueAsString(sl);
        this.mvc.perform(get("/shopping/"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));


        this.mvc.perform(get("/shopping/RM/-2"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(sl)));

        ArrayList<ShoppingList> sl1 = new ArrayList<>();
        this.mvc.perform(get("/shopping/RM/-22"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(sl1)));

    }

    @After
    public void tearDown()
    {

    }
}
