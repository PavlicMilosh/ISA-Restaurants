package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.ulitity.Utilities;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;

/**
 * Created by Q on 18-Jun-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class InvitationRepositoryTest
{
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private InvitationRepository invitationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired TableRepository tableRepository;

    private MockMvc mvc;


    private Long reservation_id;
    private Long invitation1_id;
    private Long g1_id;
    private Long g2_id;


    @Before
    public void setUp()
    {
        Guest reserver = new Guest();

        Guest g1 = new Guest("g1", "g1", "Guest1", "Guestovic1");
        Guest g2 = new Guest("g2", "g2", "Guest2", "Guestovic2");
        userRepository.save(g1);
        userRepository.save(g2);
        g1_id = g1.getId();
        g2_id = g2.getId();

        Reservation reservation = new Reservation();
        reservation.setReserver(g1);
        reservationRepository.save(reservation);
        reservation_id = reservation.getId();

        Invitation invitation = new Invitation();
        invitation.setStatus(InvitationStatus.ACCEPTED);
        invitation.setReservation(reservation);
        invitation.setInvited(g2);
        invitationRepository.save(invitation);
        invitation1_id = invitation.getId();

        reservation.addInvitation(invitation);
        reservationRepository.save(reservation);
    }


    @Test
    public void testFindUserByIdAndInvitationId()
    {
        Invitation expected = invitationRepository.findOne(invitation1_id);
        Invitation actual = invitationRepository.findByUserIdAndInvitationId(g2_id, invitation1_id);
        Assert.assertEquals(expected, actual);
    }


    @Test
    public void testGetUsersAcceptedInvitationsByUserId()
    {
        Invitation expected = invitationRepository.findOne(invitation1_id);
        List<Invitation> actual = invitationRepository.getUsersAcceptedInvitationsByUserId(g2_id);
        Assert.assertEquals(expected, actual.get(0));
    }


    @After
    public void tearDown()
    {
        reservationRepository.delete(invitation1_id);
        userRepository.delete(g1_id);
        userRepository.delete(g2_id);
    }
}
