package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Drink;
import com.isa.restaurant.domain.DrinkMark;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.User;
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
public class DrinkMarkRepositoryTest
{
    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DrinkMarkRepository drinkMarkRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByDrinkId()
    {
        Drink drink = new Drink();
        Drink savedDrink = drinkRepository.save(drink);
        Guest guest = new Guest("aaaa.aaa@hotmail.com", "password1278", "firstName890", "lastName000");
        Guest savedGust=(Guest) userRepository.save(guest);
        DrinkMark drinkMark = new DrinkMark(5.0, savedGust, savedDrink);
        drinkMarkRepository.save(drinkMark);
        DrinkMark getDrinkMark = drinkMarkRepository.findByDrinkId(drink.getId());
        Assert.assertEquals(drinkMark, getDrinkMark);
    }
}
