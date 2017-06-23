package com.isa.restaurant;

import com.isa.restaurant.domain.DishType;
import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.Waiter;
import com.isa.restaurant.repositories.InvitationRepository;
import com.isa.restaurant.repositories.UserRepository;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Q on 23-Jun-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VerificationIntegrationTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InvitationRepository restaurantRepository;

    private MockMvc mvc;

    private Long registrationGuestId = -10L;
    private Long invitationGuestId = -12L;
    private Long invitationId = -10L;
    private String registrationToken = "registrationTokenSample";
    private String invitationToken = "invitationTokenSample";


    @Before
    public void setUp()
    {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void testEnablingUser()
            throws Exception
    {
        String urlTemplate = "/guest/" + registrationGuestId + "/activation/" + registrationToken;
        this.mvc.perform(get(urlTemplate)).andExpect(status().isOk());

        urlTemplate = "/guest/" + -125831 + "/activation/" + registrationToken;
        this.mvc.perform(get(urlTemplate)).andExpect(status().isForbidden());

        urlTemplate = "/guest/" + registrationGuestId + "/activation/" + registrationToken;
        this.mvc.perform(get(urlTemplate)).andExpect(status().isConflict());
    }


    @Test
    public void testAcceptingInvitation()
            throws Exception
    {
        String urlTemplate = "/guest/" + invitationGuestId + "/acceptInvitation/" + invitationId + "/" + invitationToken;
        this.mvc.perform(put(urlTemplate)).andExpect(status().isOk());

        urlTemplate = "/guest/" + -121212 + "/acceptInvitation/" + invitationId + "/" + invitationToken;
        this.mvc.perform(put(urlTemplate)).andExpect(status().isForbidden());

        urlTemplate = "/guest/" + invitationGuestId + "/acceptInvitation/" + invitationId + "/" + invitationToken;
        this.mvc.perform(put(urlTemplate)).andExpect(status().isConflict());
    }


}
