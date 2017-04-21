package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.SystemManager;
import com.isa.restaurant.domain.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Milos on 11-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest
{

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp()
    {
        userRepository.save(new SystemManager("pera", "pera", "Pera", "Peric"));
        userRepository.save(new SystemManager("zika", "zika", "Zika", "Zikic"));
    }

    @Test
    public void testFindByUsername()
    {
        User pera = userRepository.findByEmail("pera");
        User zika = userRepository.findByEmail("zika");
        Assert.assertEquals(new SystemManager("pera", "pera", "Pera", "Peric"), pera);
        Assert.assertEquals(new SystemManager("zika", "zika", "Zika", "Zikic"), zika);
    }

    @Test
    public void testSave()
    {
        User steva = new SystemManager("steva", "steva", "Steva", "Stevic");    //insert
        User savedSteva = userRepository.save(steva);
        Assert.assertEquals(steva, savedSteva);
    }

    @After
    public void tearDown()
    {
        User pera = userRepository.findByEmail("pera");
        User zika = userRepository.findByEmail("zika");
        User steva = userRepository.findByEmail("steva");
        userRepository.delete(pera.getId());
        userRepository.delete(zika.getId());
        if(steva != null)
            userRepository.delete(steva.getId());
    }
}
