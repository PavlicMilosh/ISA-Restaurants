package com.isa.restaurant;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.repositories.*;
import com.isa.restaurant.services.ReservationService;

import com.isa.restaurant.ulitity.Utilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Repository;
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
@Transactional
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

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private ReservationRepository reservationRepository;


    private MockMvc mvc;

    private Long g1_id;
    private Long g2_id;
    private Long g3_id;
    private Long g4_id;

    private Long f_g1_g2_id;
    private Long f_g1_g3_id;
    private Long f_g1_g4_id;

    private Long t1_id;
    private Long t2_id;
    private Long t3_id;
    private Long region_id;
    private Long dish_id;
    private Long drink_id;
    private Long restaurant_id;


    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        Guest g1 = new Guest("g1", "g1", "Guest1", "Guestovic1");
        Guest g2 = new Guest("g2", "g2", "Guest2", "Guestovic2");
        Guest g3 = new Guest("g3", "g3", "Guest3", "Guestovic3");
        Guest g4 = new Guest("g4", "g4", "Guest4", "Guestovic4");
        userRepository.save(g1);
        userRepository.save(g2);
        userRepository.save(g3);
        userRepository.save(g4);
        g1_id = g1.getId();
        g2_id = g2.getId();
        g3_id = g3.getId();
        g4_id = g4.getId();

        Friendship f_g1_g2 = new Friendship(g1, g2);
        f_g1_g2.setActionUser(g1);
        f_g1_g2.setStatus(FriendshipStatus.ACCEPTED);

        Friendship f_g1_g3 = new Friendship(g1, g3);
        f_g1_g3.setActionUser(g1);
        f_g1_g3.setStatus(FriendshipStatus.ACCEPTED);

        Friendship f_g1_g4 = new Friendship(g1, g4);
        f_g1_g4.setActionUser(g1);
        f_g1_g4.setStatus(FriendshipStatus.ACCEPTED);

        friendshipRepository.save(f_g1_g2);
        friendshipRepository.save(f_g1_g3);
        friendshipRepository.save(f_g1_g4);
        f_g1_g2_id = f_g1_g2.getId();
        f_g1_g3_id = f_g1_g3.getId();
        f_g1_g4_id = f_g1_g4.getId();

        Restaurant restaurant = new Restaurant();
        restaurant.setName("Test Restaurant");
        restaurant.setDescription("Some description");
        restaurant.setDishes(new HashSet<>());
        restaurant.setDrinks(new HashSet<>());
        restaurantRepository.save(restaurant);
        restaurant_id = restaurant.getId();

        Region region = new Region();
        restaurant.addRegion(region);
        region.setRestaurant(restaurant);
        restaurantRepository.save(restaurant);
        regionRepository.save(region);

        RestaurantTable t1 = new RestaurantTable();
        t1.setRestaurant(restaurant);
        t1.setRegion(region);
        region.addTable(t1);

        RestaurantTable t2 = new RestaurantTable();
        t2.setRestaurant(restaurant);
        t2.setRegion(region);
        region.addTable(t2);

        RestaurantTable t3 = new RestaurantTable();
        t3.setRestaurant(restaurant);
        t3.setRegion(region);
        region.addTable(t3);

        t1 = tableRepository.save(t1);
        t2 = tableRepository.save(t2);
        t3 = tableRepository.save(t3);
        t1_id = t1.getId();
        t2_id = t2.getId();
        t3_id = t3.getId();
        restaurant.addTable(t1);
        restaurant.addTable(t2);
        restaurant.addTable(t3);

        regionRepository.save(region);
        restaurantRepository.save(restaurant);

        Drink drink = new Drink("drink", "desc", 800.0, restaurant);
        Dish dish = new Dish("dish", "desc", 800.0, restaurant);
        drinkRepository.save(drink);
        dishRepository.save(dish);
        drink_id = drink.getId();
        dish_id = dish.getId();
        restaurant.addDrink(drink);
        restaurant.addDish(dish);
        restaurantRepository.save(restaurant);
    }


    @Test
    public void testSendReservation()
            throws Exception
    {
        ObjectMapper om = new ObjectMapper();

        Guest reserver = (Guest) userRepository.findById(g1_id);
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReserver(new GuestDTO(reserver));
        reservationDTO.setRestaurant(new RestaurantDTO(restaurantRepository.findOne(restaurant_id)));

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        Date date = c.getTime();
        String start = df.format(date);

        reservationDTO.setStartDate(start);
        reservationDTO.setStartTime("12:00");
        reservationDTO.setDuration(90L);

        Set<DrinkOrderDTO> drinkOrders = new HashSet<>();
        Set<DishOrderDTO> dishOrders = new HashSet<>();
        Set<GuestDTO> invites = new HashSet<>();
        Set<RestaurantTableDTO> tables = new HashSet<>();

        drinkOrders.add(new DrinkOrderDTO(drinkRepository.findById(drink_id), 5));
        dishOrders.add(new DishOrderDTO(dishRepository.findById(dish_id), 5));
        invites.add(new GuestDTO((Guest) userRepository.findById(g2_id)));
        invites.add(new GuestDTO((Guest) userRepository.findById(g3_id)));
        tables.add(new RestaurantTableDTO(tableRepository.findOne(t1_id), false));
        reservationDTO.setDrinkOrders(drinkOrders);
        reservationDTO.setDishOrders(dishOrders);
        reservationDTO.setInvites(invites);
        reservationDTO.setTables(tables);

        String urlTemplate = "/guest/" + g1_id + "/sendReservation";

        ReservationDTO actual = reservationDTO;
        actual.setId(1L);

        // send free
        this.mvc.perform(
                post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(reservationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(actual)));

        // send taken
        this.mvc.perform(
                post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(reservationDTO)))
                .andExpect(status().isUnprocessableEntity());

    }


    @Test
    public void testUpdateReservation()
            throws Exception
    {
        ObjectMapper om = new ObjectMapper();

        Guest reserver = (Guest) userRepository.findById(g1_id);
        ReservationDTO reservationDTO = new ReservationDTO();
        reservationDTO.setReserver(new GuestDTO(reserver));
        reservationDTO.setRestaurant(new RestaurantDTO(restaurantRepository.findOne(restaurant_id)));

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);
        Date date = c.getTime();
        String start = df.format(date);

        reservationDTO.setStartDate(start);
        reservationDTO.setStartTime("12:00");
        reservationDTO.setDuration(90L);

        Set<DrinkOrderDTO> drinkOrders = new HashSet<>();
        Set<DishOrderDTO> dishOrders = new HashSet<>();
        Set<GuestDTO> invites = new HashSet<>();
        Set<RestaurantTableDTO> tables = new HashSet<>();

        drinkOrders.add(new DrinkOrderDTO(drinkRepository.findById(drink_id), 5));
        dishOrders.add(new DishOrderDTO(dishRepository.findById(dish_id), 5));
        invites.add(new GuestDTO((Guest) userRepository.findById(g2_id)));
        invites.add(new GuestDTO((Guest) userRepository.findById(g3_id)));
        tables.add(new RestaurantTableDTO(tableRepository.findOne(t1_id), false));
        reservationDTO.setDrinkOrders(drinkOrders);
        reservationDTO.setDishOrders(dishOrders);
        reservationDTO.setInvites(invites);
        reservationDTO.setTables(tables);

        String urlTemplate1 = "/guest/" + g1_id + "/sendReservation";
        String urlTemplate2 = "/guest/" + g1_id + "/updateReservation/" + 1;

        ReservationDTO actual = reservationDTO;
        actual.setId(1L);

        // send reservation
        this.mvc.perform(
                post(urlTemplate1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(reservationDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(actual)));

        ReservationUpdateDTO updateDTO = new ReservationUpdateDTO();
        updateDTO.setGuestId(g1_id);
        updateDTO.setDishOrders(new HashSet<>());
        updateDTO.setDrinkOrders(new HashSet<>());
        updateDTO.setReservationId(1L);
        Reservation r = reservationRepository.findById(1L);
        updateDTO.setOrderId(r.getOrderByUserId(g1_id).getId());

        reservationDTO.setDishOrders(new HashSet<>());
        reservationDTO.setDrinkOrders(new HashSet<>());
        // update it
        this.mvc.perform(
                put(urlTemplate2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(updateDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(reservationDTO)));

        ;
    }


    @Test
    public void testGetReservation()
    {

    }


    @Test
    public void testAcceptInvitation()
    {

    }


    @Test
    public void testGetAcceptedInvitations()
    {

    }


    @Test
    public void testGetHistoryOfVisits()
    {

    }



    @After
    public void tearDown()
    {
    }
}
