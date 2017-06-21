package com.isa.restaurant.search;

import com.isa.restaurant.domain.DTO.GuestAndRelationDTO;
import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.repositories.FriendshipRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.FriendshipService;
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

import java.util.List;

/**
 * Created by Q on 14-May-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class GuestSearchTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipService friendshipService;

    private MockMvc mvc;

    private Long g1_id;
    private Long g2_id;
    private Long g3_id;
    private Long g7_id;

    private Long f_g1_g2_id;
    private Long f_g1_g3_id;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        Guest g1 = new Guest("g1", "g1", "Guest1", "Guestovic1");
        Guest g2 = new Guest("g2", "g2", "Guest2", "Guestovic2");
        Guest g3 = new Guest("g3", "g3", "Guest3", "Guestovic3");
        Guest g7 = new Guest("g7", "g7", "Guest7", "Guestovic7");

        Friendship f_g1_g2 = new Friendship(g1, g2);
        f_g1_g2.setActionUser(g1);
        f_g1_g2.setStatus(FriendshipStatus.ACCEPTED);

        Friendship f_g1_g3 = new Friendship(g1, g3);
        f_g1_g3.setActionUser(g1);
        f_g1_g3.setStatus(FriendshipStatus.DECLINED);


        userRepository.save(g1);
        userRepository.save(g2);
        userRepository.save(g3);
        userRepository.save(g7);

        friendshipRepository.save(f_g1_g2);
        friendshipRepository.save(f_g1_g3);

        g1_id = g1.getId();
        g2_id = g2.getId();
        g3_id = g3.getId();
        g7_id = g7.getId();

        f_g1_g2_id = f_g1_g2.getId();
        f_g1_g3_id = f_g1_g3.getId();
    }

    @Test
    public void testSearchAllGuests()
    {
        // ACCEPTED
        List<GuestAndRelationDTO> results = friendshipService.searchAllGuests("guest2", g1_id);
        Guest g = (Guest) userRepository.findById(g2_id);
        Friendship f = friendshipRepository.findByBothUsers(g1_id, g2_id);
        GuestAndRelationDTO expected = new GuestAndRelationDTO(g, f.getStatus(), f.getActionUser().getId());
        GuestAndRelationDTO got = results.get(0);
        Assert.assertEquals(expected, got);


        // DECLINED
        results = friendshipService.searchAllGuests("guest3", g1_id);
        g = (Guest) userRepository.findById(g3_id);
        f = friendshipRepository.findByBothUsers(g1_id, g3_id);
        expected = new GuestAndRelationDTO(g, f.getStatus(), f.getActionUser().getId());
        got = results.get(0);
        Assert.assertEquals(expected, got);


        // NONE
        results = friendshipService.searchAllGuests("guest7", g1_id);
        g = (Guest) userRepository.findById(g7_id);
        expected = new GuestAndRelationDTO(g, FriendshipStatus.NONE, null);
        got = results.get(0);
        Assert.assertEquals(expected, got);
    }


    @Test
    public void testSearchUserFriends()
    {
        // POSITIVE
        List<GuestAndRelationDTO> results = friendshipService.searchUserFriends("guest2", g1_id);
        Guest g = (Guest) userRepository.findById(g2_id);
        Friendship f = friendshipRepository.findByBothUsers(g1_id, g2_id);
        GuestAndRelationDTO expected = new GuestAndRelationDTO(g, f.getStatus(), f.getActionUser().getId());
        GuestAndRelationDTO got = results.get(0);
        Assert.assertEquals(expected, got);


        //NEGATIVE
        results = friendshipService.searchUserFriends("NO_GUEST_WITH_THIS_NAME", g1_id);
        int expectedSize = 0;
        int gotSize = results.size();
        Assert.assertEquals(expectedSize, gotSize);
    }


    @After
    public void tearDown()
    {
        friendshipRepository.delete(f_g1_g2_id);
        friendshipRepository.delete(f_g1_g3_id);

        userRepository.delete(g1_id);
        userRepository.delete(g2_id);
        userRepository.delete(g3_id);
        userRepository.delete(g7_id);
    }
}
