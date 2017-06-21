package com.isa.restaurant.repositories;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.Reservation;
import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.RestaurantTable;
import com.isa.restaurant.ulitity.Utilities;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Q on 14-May-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ReservationRepositoryTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;


    private MockMvc mvc;

    private Long r1_id;
    private Long r2_id;
    private Long g_id;
    private Long res_id;

    private Date dateReservationStart;
    private Date dateReservationEnd;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        Restaurant r1 = new Restaurant();
        r1.setName("Restaurant by R1");
        r1.setDescription("CommonWord shared1 unique -> 11");

        Restaurant r2 = new Restaurant();
        r2.setName("Restaurant by R2");
        r2.setDescription("CommonWord shared2 unique -> 22");

        Guest guest = new Guest("g1", "pass", "Guest", "Guestovic");

        dateReservationStart = Calendar.getInstance().getTime();
        dateReservationEnd = Utilities.addMinutesToDate(dateReservationStart, 30L);
        Reservation reservation = new Reservation();
        reservation.setRestaurant(r1);
        reservation.setReserver(guest);
        reservation.setDateTimeStart(dateReservationStart);
        reservation.setDateTimeEnd(dateReservationEnd);

        userRepository.save(guest);
        restaurantRepository.save(r1);
        restaurantRepository.save(r2);
        reservationRepository.save(reservation);

        g_id = guest.getId();
        r1_id = r1.getId();
        r2_id = r2.getId();
        res_id = reservation.getId();
    }


    @Test
    public void testGetReservationsByRestaurantAndDate()
    {
        Date dateStart = Utilities.addMinutesToDate(dateReservationStart, -30L);
        Date dateEnd = Utilities.addMinutesToDate(dateReservationEnd, 30L);

        Reservation expected = reservationRepository.findOne(res_id);
        List<Reservation> reservations = reservationRepository.getReservationsByRestaurantAndDate(res_id, dateStart, dateEnd);

        Reservation got =reservations.get(0);

        Assert.assertEquals(expected, got);
    }


    @Test
    public void testGetReservationsByReserverId()
    {
        Reservation expected = reservationRepository.findById(res_id);
        List<Reservation> actual = reservationRepository.getReservationsByReserverId(g_id);
        Assert.assertEquals(expected, actual.get(0));
    }

    @After
    public void tearDown()
    {
        reservationRepository.delete(res_id);
        restaurantRepository.delete(r1_id);
        restaurantRepository.delete(r2_id);
        userRepository.delete(g_id);
    }
}
