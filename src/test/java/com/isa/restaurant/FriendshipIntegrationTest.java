package com.isa.restaurant;

import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.repositories.FriendshipRepository;
import com.isa.restaurant.repositories.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Q on 17-Apr-17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FriendshipIntegrationTest
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
    private Long g4_id;
    private Long g5_id;
    private Long g6_id;
    private Long g7_id;

    private Long f_g1_g2_id;
    private Long f_g1_g3_id;
    private Long f_g5_g6_id;
    private Long f_g5_g7_id;

    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();

        Guest g1 = new Guest("g1", "g1", "Guest1", "Guestovic1");
        Guest g2 = new Guest("g2", "g2", "Guest2", "Guestovic2");
        Guest g3 = new Guest("g3", "g3", "Guest3", "Guestovic3");
        Guest g4 = new Guest("g4", "g4", "Guest4", "Guestovic4");
        Guest g5 = new Guest("g5", "g5", "Guest5", "Guestovic5");
        Guest g6 = new Guest("g6", "g6", "Guest6", "Guestovic6");
        Guest g7 = new Guest("g7", "g7", "Guest7", "Guestovic7");

        userRepository.save(g1);
        userRepository.save(g2);
        userRepository.save(g3);
        userRepository.save(g4);
        userRepository.save(g5);
        userRepository.save(g6);
        userRepository.save(g7);

        g1_id = g1.getId();
        g2_id = g2.getId();
        g3_id = g3.getId();
        g4_id = g4.getId();
        g5_id = g5.getId();
        g6_id = g6.getId();
        g7_id = g7.getId();


        Friendship f_g1_g2 = new Friendship(g1, g2);
        f_g1_g2.setActionUser(g1);
        f_g1_g2.setStatus(FriendshipStatus.ACCEPTED);

        Friendship f_g1_g3 = new Friendship(g1, g3);
        f_g1_g3.setActionUser(g1);
        f_g1_g3.setStatus(FriendshipStatus.DECLINED);


        Friendship f_g5_g6 = new Friendship(g5, g6);
        f_g5_g6.setActionUser(g5);
        f_g5_g6.setStatus(FriendshipStatus.PENDING);

        Friendship f_g5_g7 = new Friendship(g5, g7);
        f_g5_g7.setActionUser(g5);
        f_g5_g7.setStatus(FriendshipStatus.DECLINED);


        friendshipRepository.save(f_g1_g2);
        friendshipRepository.save(f_g1_g3);
        friendshipRepository.save(f_g5_g6);
        friendshipRepository.save(f_g5_g7);

        f_g1_g2_id = f_g1_g2.getId();
        f_g1_g3_id = f_g1_g3.getId();
        f_g5_g6_id = f_g5_g6.getId();
        f_g5_g7_id = f_g5_g7.getId();
    }


    @Test
    public void testSendingFriendRequest() throws Exception
    {
        // send to self
        String urlTemplate = "/guest/" + g1_id + "/sendFriendRequest";
        this.mvc.perform(
                    post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(g1_id.toString()))
                .andExpect(status().isConflict());

        // send to accepted
        this.mvc.perform(
                    post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(g2_id.toString()))
                .andExpect(status().isConflict());

        // send to declined or pending (resend)
        this.mvc.perform(
                post(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(g3_id.toString()))
                .andExpect(status().isCreated());

        // send to new
        this.mvc.perform(
                post(urlTemplate)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(g4_id.toString()))
                .andExpect(status().isCreated());
    }


    @Test
    public void testAcceptingFriendRequest() throws Exception
    {
        String urlTemplate = "/guest/" + g6_id + "/acceptFriendRequest";
        this.mvc.perform(
                put(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(f_g5_g6_id.toString()))
                .andExpect(status().isOk());
    }


    @Test
    public void testDecliningFriendRequest() throws Exception
    {
        String urlTemplate = "/guest/" + g7_id + "/declineFriendRequest";
        this.mvc.perform(
                put(urlTemplate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(f_g5_g7_id.toString()))
                .andExpect(status().isOk());
    }


    @After
    public void tearDown()
    {
        Friendship f_g1_g4 = friendshipRepository.findByBothUsers(g1_id, g4_id);
        if (f_g1_g4 != null)
            friendshipRepository.delete(f_g1_g4.getId());


        friendshipRepository.delete(f_g1_g2_id);
        friendshipRepository.delete(f_g1_g3_id);
        friendshipRepository.delete(f_g5_g6_id);
        friendshipRepository.delete(f_g5_g7_id);

        userRepository.delete(g1_id);
        userRepository.delete(g2_id);
        userRepository.delete(g3_id);
        userRepository.delete(g4_id);
        userRepository.delete(g5_id);
        userRepository.delete(g6_id);
        userRepository.delete(g7_id);
    }
}
