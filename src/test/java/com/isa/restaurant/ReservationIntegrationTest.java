package com.isa.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.GuestDTO;
import com.isa.restaurant.domain.DTO.ReservationDTO;
import com.isa.restaurant.domain.DTO.RestaurantDTO;
import com.isa.restaurant.domain.DTO.RestaurantTableDTO;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.ReservationService;

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
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Q on 13-May-17.
 */

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReservationIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private TableRepository tableRepository;


    private MockMvc mvc;

    private Long g1_id;
    private Long t1_id;
    private Long t2_id;
    private Long t3_id;
    private Long restaurant_id;


    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        Guest g1 = new Guest("g1", "g1", "Guest1", "Guestovic1");

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setDescription("Some description");
        restaurant.setDishes(new HashSet<>());
        restaurant.setDrinks(new HashSet<>());
        restaurantRepository.save(restaurant);

        RestaurantTable t1 = new RestaurantTable();
        RestaurantTable t2 = new RestaurantTable();
        RestaurantTable t3 = new RestaurantTable();
        t1.setRestaurant(restaurant);
        t2.setRestaurant(restaurant);
        t3.setRestaurant(restaurant);
        tableRepository.save(t1);
        tableRepository.save(t2);
        tableRepository.save(t3);

        restaurant.addTable(t1);
        restaurant.addTable(t2);
        restaurant.addTable(t3);
        restaurantRepository.save(restaurant);

        userRepository.save(g1);

        g1_id = g1.getId();
        t1_id = t1.getId();
        t2_id = t2.getId();
        t3_id = t3.getId();
        restaurant_id = restaurant.getId();
    }

    @Test
    public void testSendReservation()
            throws Exception
    {
        ObjectMapper om = new ObjectMapper();

        Guest reserver = (Guest) userRepository.findById(g1_id);
        Restaurant restaurant = restaurantRepository.findOne(restaurant_id);
        RestaurantTable restaurantTable = tableRepository.findOne(t1_id);
        Set<RestaurantTableDTO> tables = new HashSet<>();
        tables.add(new RestaurantTableDTO(restaurantTable, false));

        Date now = Calendar.getInstance().getTime();
        DateFormat dfDate = new SimpleDateFormat("dd.MM.yyyy.");
        DateFormat dfTime = new SimpleDateFormat("HH:mm");

        ReservationDTO newReservationDTO = new ReservationDTO();
        newReservationDTO.setDuration(60L);
        newReservationDTO.setStartDate(dfDate.format(now));
        newReservationDTO.setStartTime(dfTime.format(now));
        newReservationDTO.setTables(tables);
        newReservationDTO.setRestaurant(new RestaurantDTO(restaurant));

        ReservationDTO expected = new ReservationDTO();
        expected.setDuration(60L);
        expected.setStartDate(dfDate.format(now));
        expected.setStartTime(dfTime.format(now));
        expected.setTables(tables);
        expected.setRestaurant(new RestaurantDTO(restaurant));
        expected.setInvites(new HashSet<>());
        expected.setReserver(new GuestDTO(reserver));
        expected.setId(1L);

        String urlTemplate = "/guest/" + g1_id + "/sendReservation";
        this.mvc.perform(
                post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(om.writeValueAsString(newReservationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(expected)));




    }


    @After
    public void tearDown()
    {
    }
}
