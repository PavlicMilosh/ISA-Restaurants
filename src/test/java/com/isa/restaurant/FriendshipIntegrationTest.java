package com.isa.restaurant;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isa.restaurant.domain.DTO.FriendshipDTO;
import com.isa.restaurant.domain.DTO.GuestAndRelationDTO;
import com.isa.restaurant.domain.DTO.GuestDTO;
import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.repositories.FriendshipRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.search.GuestSearch;
import com.isa.restaurant.services.FriendshipService;
import org.junit.After;
import org.junit.Assert;
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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Autowired
    private FriendshipService friendshipService;


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

        userRepository.save(g1);
        userRepository.save(g2);
        userRepository.save(g3);
        userRepository.save(g4);
        userRepository.save(g5);
        userRepository.save(g6);
        userRepository.save(g7);

        friendshipRepository.save(f_g1_g2);
        friendshipRepository.save(f_g1_g3);
        friendshipRepository.save(f_g5_g6);
        friendshipRepository.save(f_g5_g7);

        g1_id = g1.getId();
        g2_id = g2.getId();
        g3_id = g3.getId();
        g4_id = g4.getId();
        g5_id = g5.getId();
        g6_id = g6.getId();
        g7_id = g7.getId();

        f_g1_g2_id = f_g1_g2.getId();
        f_g1_g3_id = f_g1_g3.getId();
        f_g5_g6_id = f_g5_g6.getId();
        f_g5_g7_id = f_g5_g7.getId();
    }


    @Test
    public void testSendingFriendRequest() throws Exception
    {
        ObjectMapper om = new ObjectMapper();

        // send to self
        String urlTemplate = "/guest/" + g1_id + "/sendFriendRequest/";
        this.mvc.perform(
                post(urlTemplate + g1_id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

        // send to accepted
        this.mvc.perform(
                post(urlTemplate + g2_id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());

        // send to declined or pending (resend)
        Friendship friendship = friendshipRepository.findById(f_g1_g3_id);
        FriendshipDTO friendshipDTO = new FriendshipDTO(friendship);
        friendshipDTO.setStatus(FriendshipStatus.PENDING);

        this.mvc.perform(
                post(urlTemplate + g3_id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(om.writeValueAsString(friendshipDTO)));

        // send to new
        this.mvc.perform(
                post(urlTemplate + g4_id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    public void testAcceptingFriendRequest() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        FriendshipDTO friendshipDTO = new FriendshipDTO(friendshipRepository.findById(f_g5_g6_id));
        friendshipDTO.setStatus(FriendshipStatus.ACCEPTED);
        friendshipDTO.setActionUserEmail("g6");
        friendshipDTO.setActionUserId(g6_id);

        String urlTemplate = "/guest/" + g6_id + "/acceptFriendRequest/" + g5_id;
        this.mvc.perform(put(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(friendshipDTO)));
    }


    @Test
    public void testDecliningFriendRequest() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        FriendshipDTO friendshipDTO = new FriendshipDTO(friendshipRepository.findById(f_g5_g6_id));
        friendshipDTO.setStatus(FriendshipStatus.DECLINED);
        friendshipDTO.setActionUserEmail("g6");
        friendshipDTO.setActionUserId(g6_id);

        String urlTemplate = "/guest/" + g6_id + "/declineFriendRequest/" + g5_id;
        this.mvc.perform(put(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(friendshipDTO)));
    }


    @Test
    public void testUnfriendUser() throws Exception
    {
        ObjectMapper om = new ObjectMapper();

        FriendshipDTO friendshipDTO = new FriendshipDTO(friendshipRepository.findById(f_g1_g2_id));
        friendshipDTO.setStatus(FriendshipStatus.UNFRIENDED);
        friendshipDTO.setActionUserEmail("g2");
        friendshipDTO.setActionUserId(g2_id);

        String urlTemplate = "/guest/" + g2_id + "/unfriendUser/";
        this.mvc.perform(put(urlTemplate + g1_id))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(friendshipDTO)));

        this.mvc.perform(put(urlTemplate + g7_id))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetFriendRequests() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        Guest request = (Guest) userRepository.findById(g5_id);
        Set<GuestDTO> requests = new HashSet<>();
        requests.add(new GuestDTO(request));

        String urlTemplate = "/guest/" + g6_id + "/getFriendRequests";
        this.mvc.perform(
                get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(requests)));

        urlTemplate = "/guest/10001010/getFriendRequests";
        this.mvc.perform(
                get(urlTemplate))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testGetFriends() throws Exception
    {
        ObjectMapper om = new ObjectMapper();
        Set<GuestDTO> friends = new HashSet<>();
        Guest g1 = (Guest)userRepository.findById(g1_id);
        friends.add(new GuestDTO(g1));

        String urlTemplate = "/guest/" + g2_id + "/getFriends";
        this.mvc.perform(
                get(urlTemplate))
                .andExpect(status().isOk())
                .andExpect(content().json(om.writeValueAsString(friends)));

        urlTemplate = "/guest/1010110/getFriends";
        this.mvc.perform(
                get(urlTemplate))
                .andExpect(status().isNotFound());
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
