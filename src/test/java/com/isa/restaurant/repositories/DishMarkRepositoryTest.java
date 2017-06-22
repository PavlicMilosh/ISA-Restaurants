package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by djuro on 6/22/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DishMarkRepositoryTest
{
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishMarkRepository dishMarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByDishId()
    {
        Dish dish = new Dish();
        Dish savedDish = dishRepository.save(dish);
        Guest guest = new Guest("aaaa.aaa1@hotmail.com", "password1278", "firstName890", "lastName000");
        Guest savedGust=(Guest) userRepository.save(guest);
        DishMark dishMark = new DishMark(5.0, savedGust, savedDish);
        dishMarkRepository.save(dishMark);
        DishMark getDishMark = dishMarkRepository.findByDishId(dish.getId());
        Assert.assertEquals(dishMark, getDishMark);
    }
}
