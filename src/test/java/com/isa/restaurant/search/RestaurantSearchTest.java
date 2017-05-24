package com.isa.restaurant.search;

import com.isa.restaurant.domain.DTO.RestaurantDTO;
import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.repositories.RestaurantRepository;
import com.isa.restaurant.services.RestaurantService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * Created by Q on 14-May-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestaurantSearchTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private RestaurantService restaurantService;

    private MockMvc mvc;

    private Long guest_id;
    private Long r1_id;
    private Long r2_id;
    private Long r3_id;
    private Long r4_id;


    @Before
    public void setUp()
    {
        Restaurant r1 = new Restaurant();
        r1.setName("Restaurant by R1");
        r1.setDescription("CommonWord shared1 unique -> 11");

        Restaurant r2 = new Restaurant();
        r2.setName("Restaurant by R2");
        r2.setDescription("CommonWord shared2 unique -> 22");

        Restaurant r3 = new Restaurant();
        r3.setName("Restaurant by R3");
        r3.setDescription("CommonWord shared3 unique -> 33");


        Restaurant r4 = new Restaurant();
        r4.setName("Restaurant by R4");
        r4.setDescription("CommonWord shared4 unique -> 44");

        restaurantRepository.save(r1);
        restaurantRepository.save(r2);
        restaurantRepository.save(r3);
        restaurantRepository.save(r4);

        r1_id = r1.getId();
        r2_id = r2.getId();
        r3_id = r3.getId();
        r4_id = r4.getId();
    }


    @Test
    public void TestSearchRestaurantsByNameAndDescription()
    {
        // BY NAME FOUND 1
        List<RestaurantDTO> restaurants = restaurantService.searchRestaurantsByNameAndDescription("R1");
        Restaurant r = restaurantRepository.findOne(r1_id);
        RestaurantDTO expected = new RestaurantDTO(r);
        RestaurantDTO got = restaurants.get(0);
        Assert.assertEquals(expected, got);

        // BY NAME FOUND ALL
        restaurants = restaurantService.searchRestaurantsByNameAndDescription("restaurant by");
        int expectedSize = 4;
        int gotSize = restaurants.size();
        Assert.assertEquals(expectedSize, gotSize);

        // BY NAME FOUND ALL
        restaurants = restaurantService.searchRestaurantsByNameAndDescription("restAurAnt");
        expectedSize = 4;
        gotSize = restaurants.size();
        Assert.assertEquals(expectedSize, gotSize);

        //BY NAME FOUND 0
        restaurants = restaurantService.searchRestaurantsByNameAndDescription("R123");
        r = restaurantRepository.findOne(r1_id);
        expectedSize = 0;
        gotSize = restaurants.size();
        Assert.assertEquals(expectedSize, gotSize);

        //BY DESCRIPTION FOUND ALL
        restaurants = restaurantService.searchRestaurantsByNameAndDescription("CommonWord");
        expectedSize = 4;
        gotSize = restaurants.size();
        Assert.assertEquals(expectedSize, gotSize);
    }


    @After
    public void tearDown()
    {
        restaurantRepository.delete(r1_id);
        restaurantRepository.delete(r2_id);
        restaurantRepository.delete(r3_id);
        restaurantRepository.delete(r4_id);
    }
}
