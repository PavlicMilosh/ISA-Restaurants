package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.VerificationToken;
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

/**
 * Created by Q on 14-May-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class VerificationTokenRepositoryTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mvc;
    private Long guest_id;
    private Long token_id;
    private String token_value;

    @Before
    public void setUp()
    {
        Guest guest = new Guest("g1", "pass", "Guest", "Guestovic");
        VerificationToken verificationToken = new VerificationToken(guest, 30);
        token_value = "TEST_TOKEN_VALUE";
        verificationToken.setToken(token_value);
        userRepository.save(guest);
        verificationTokenRepository.save(verificationToken);
        token_id = verificationToken.getId();
        guest_id = guest.getId();
    }


    @Test
    public void testFindById()
    {
        Guest guest = (Guest) userRepository.findById(guest_id);
        VerificationToken got = verificationTokenRepository.findById(token_id);
        VerificationToken expected = new VerificationToken(guest, 30);
        expected.setToken(token_value);
        Assert.assertEquals(got, expected);
    }


    @Test
    public void testFindByToken()
    {
        Guest guest = (Guest) userRepository.findById(guest_id);
        VerificationToken got = verificationTokenRepository.findByToken(token_value);
        VerificationToken expected = new VerificationToken(guest, 30);
        expected.setToken(token_value);
        Assert.assertEquals(got, expected);
    }


    @Test
    public void testFindTokenByUserId()
    {
        String got = verificationTokenRepository.findTokenByUserId(token_id);
        String expected = token_value;
        Assert.assertEquals(got, expected);
    }


    @After
    public void tearDown()
    {
        verificationTokenRepository.delete(token_id);
        userRepository.delete(guest_id);
    }
}
