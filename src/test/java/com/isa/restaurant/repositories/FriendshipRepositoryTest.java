package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import com.isa.restaurant.domain.Guest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.AssertionErrors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Q on 30-Apr-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FriendshipRepositoryTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mvc;

    private Long g1_id;
    private Long g2_id;
    private Long g3_id;

    private Long f_g1_g2_id;
    private Long f_g1_g3_id;


    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        Guest g1 = new Guest("g1", "g1", "Guest1", "Guestovic1");
        Guest g2 = new Guest("g2", "g2", "Guest2", "Guestovic2");
        Guest g3 = new Guest("g3", "g3", "Guest2", "Guestovic2");

        userRepository.save(g1);
        userRepository.save(g2);
        userRepository.save(g3);

        g1_id = g1.getId();
        g2_id = g2.getId();
        g3_id = g3.getId();

        Friendship f_g1_g2 = new Friendship(g1, g2);
        f_g1_g2.setActionUser(g1);
        f_g1_g2.setStatus(FriendshipStatus.ACCEPTED);

        Friendship f_g1_g3 = new Friendship(g1, g3);
        f_g1_g3.setActionUser(g1);
        f_g1_g3.setStatus(FriendshipStatus.PENDING);

        friendshipRepository.save(f_g1_g2);
        friendshipRepository.save(f_g1_g3)
        ;
        f_g1_g2_id = f_g1_g2.getId();
        f_g1_g3_id = f_g1_g3.getId();

    }

    @Test
    public void testFindAllAcceptedFriendshipsByUserId()
    {
        Friendship accepted = friendshipRepository.findById(f_g1_g2_id);
        Set<Friendship> expected = new HashSet<>();
        expected.add(accepted);
        Set<Friendship> actual = friendshipRepository.findAllAcceptedFriendshipsByUserId(g1_id);
        Assert.assertEquals(expected, actual);

    }

    @Test
    public void testfindAllIncomingFriendRequestsByUserId()
    {
        Friendship accepted = friendshipRepository.findById(f_g1_g3_id);
        Set<Friendship> expected = new HashSet<>();
        expected.add(accepted);
        Set<Friendship> actual = friendshipRepository.findAllIncomingFriendRequestsByUserId(g3_id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFindByBothUsers()
    {
        Friendship expected = friendshipRepository.findById(f_g1_g3_id);
        Friendship actual = friendshipRepository.findByBothUsers(g3_id, g1_id);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSearchAllGuests()
    {

    }

    @Test
    public void testSearchAllFriends()
    {

    }

    @After
    public void tearDown()
    {
        friendshipRepository.delete(f_g1_g2_id);
        friendshipRepository.delete(f_g1_g3_id);

        userRepository.delete(g1_id);
        userRepository.delete(g2_id);
        userRepository.delete(g3_id);
    }
}
