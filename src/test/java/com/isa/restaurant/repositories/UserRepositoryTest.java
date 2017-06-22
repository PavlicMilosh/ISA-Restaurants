package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Provider;
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
    }

    @Test
    public void testFindByUsername()
    {
        User a = userRepository.findByEmail("a");
        User f = userRepository.findByEmail("f");
        Assert.assertEquals(-1, a.getId().longValue());
        Assert.assertEquals("a", a.getFirstName());
        Assert.assertEquals("a", a.getLastName());
        Assert.assertEquals("a", a.getEmail());
        Assert.assertEquals(-2, f.getId().longValue());
        Assert.assertEquals("f", f.getFirstName());
        Assert.assertEquals("f", f.getLastName());
        Assert.assertEquals("f", f.getEmail());
    }

    @After
    public void tearDown()
    {
    }
}
