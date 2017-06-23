package com.isa.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.RegionDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.DTO.WaiterMarkReport;
import com.isa.restaurant.repositories.RestaurantRepository;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    private UserRepository userRepository;

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
        Restaurant r = restaurantRepository.findOne(0L);
        RestaurantManager rm = (RestaurantManager) userRepository.findOne(-2L);
        Bartender b = new Bartender("bb", "bb", "bb", "bb");
        UserDTO bb = new UserDTO(b);
        bb.setId(1l);
        Cook c = new Cook("cc", "cc", "cc", "cc");
        UserDTO cc = new UserDTO(c);
        cc.setId(2l);
        DishType dishType = new DishType(r,"salate");
        Waiter w = new Waiter("ww", "ww", "ww", "ww");
        UserDTO ww = new UserDTO(w);
        ww.setId(3l);
        RestaurantManager rm1 = new RestaurantManager("rm", "rm", "rm", "rm", null);
        UserDTO rmDTO = new UserDTO(rm1);
        rmDTO.setPassword("$2a$10$V2T/wCwSHQxxG8hNXQY07uIDjawhAB8XWH1aq05X5w5hwG6wTJ1eu");
        rmDTO.setId(4L);
        this.mvc.perform(post("/restaurants/" + rm.getId() + "/addBartender")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(b)))
                .andExpect(status().isCreated())
                .andExpect((content().json(om.writeValueAsString(bb))));

        this.mvc.perform(post("/restaurants/" + rm.getId() + "/addCook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(c)))
                .andExpect(status().isCreated())
                .andExpect((content().json(om.writeValueAsString(cc))));

        this.mvc.perform(post("/restaurants/" + rm.getId() + "/addWaiter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(w)))
                .andExpect(status().isCreated())
                .andExpect((content().json(om.writeValueAsString(ww))));

        this.mvc.perform(post("/restaurants/" + r.getId() + "/addRM")
                .contentType(MediaType.APPLICATION_JSON)
                .content(om.writeValueAsString(rm1)))
                .andExpect(status().isCreated())
                .andExpect((content().json(om.writeValueAsString(rmDTO))));

    }

    @Test
    public void testGetByManager() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        Restaurant r = restaurantRepository.findOne(0L);
        this.mvc.perform(get("/restaurants/findByManagerId/-2"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetWorkersByRMId() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        ArrayList<UserDTO> users = new ArrayList<>();
        Restaurant r = restaurantRepository.findOne(0L);
        for(Bartender b : r.getBartenders())
            users.add(new UserDTO(b));
        for(Cook c : r.getCooks())
            users.add(new UserDTO(c));
        for(Waiter w : r.getWaiters())
            users.add(new UserDTO(w));
        this.mvc.perform(get("/restaurants/getWorkersByRMId/-2"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(users)));
    }

    @Test
    public void testgetRegionsByRMId() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        Restaurant r = restaurantRepository.findOne(0L);
        ArrayList<RegionDTO> regions = new ArrayList<>();
        for(Region reg : r.getRegions())
            regions.add(new RegionDTO(reg));
        this.mvc.perform(get("/restaurants/RM/-2/getRegions"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(regions)));
    }

    @Test
    public void testGetWaitersMarkReport() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        Restaurant r = restaurantRepository.findOne(0L);
        ArrayList<WaiterMarkReport> reports = new ArrayList<>();
        for(Waiter w : r.getWaiters())
        {
            WaiterMarkReport wmr = new WaiterMarkReport(w.getId(), w.getFirstName(), w.getLastName(), 9.0);
            reports.add(wmr);
        }
        this.mvc.perform(get("/restaurants/0/markReport/waiters"))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(om.writeValueAsString(reports))));
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
