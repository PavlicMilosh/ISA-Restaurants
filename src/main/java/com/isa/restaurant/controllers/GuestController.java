package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.DTO.FriendshipDTO;
import com.isa.restaurant.domain.DTO.GuestDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.services.FriendshipService;
import com.isa.restaurant.services.MailService;
import com.isa.restaurant.services.UserService;
import com.isa.restaurant.services.VerificationTokenService;
import com.isa.restaurant.services.implementation.FriendshipServiceImpl;
import com.isa.restaurant.services.implementation.MailServiceImpl;
import com.isa.restaurant.services.implementation.UserServiceImpl;
import com.isa.restaurant.services.implementation.VerificationTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Q on 15-Apr-17.
 */
@RestController
@RequestMapping(value = "/guest")

public class GuestController
{
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final VerificationTokenService verificationTokenService;
    private final MailService mailService;

    @Autowired
    public GuestController(FriendshipService friendshipService,
                           UserService userService,
                           VerificationTokenService verificationTokenService,
                           MailService mailService)
    {
        this.verificationTokenService = verificationTokenService;
        this.friendshipService = friendshipService;
        this.userService = userService;
        this.mailService = mailService;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerGuest(@RequestBody Guest guest)
    {
        UserDTO saved = userService.addGuest(guest);

        if(saved == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        String token = verificationTokenService.getTokenByUserId(guest.getId());
        mailService.sendUserActivationEmail(guest, token);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{guestId}/activation/{verificationTokenValue}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity activateUser(@PathVariable("guestId") Long guestId,
                                       @PathVariable("verificationTokenValue") String verificationTokenValue)
    {
        if (userService.findById(guestId) == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);

        Boolean activated = verificationTokenService.activateGuest(guestId, verificationTokenValue);

        if (activated)
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.CONFLICT);
    }


    @RequestMapping(value = "/{guestId}/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody Guest guest, @PathVariable Long guestId)
    {
        guest.setId(guestId);
        GuestDTO saved = userService.updateGuest(guest);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/sendFriendRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendshipDTO> sendFriendshipRequest(@RequestBody Long toWhom, @PathVariable Long guestId)
    {
        FriendshipDTO saved = friendshipService.sendRequest(guestId, toWhom);
        if (saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{guestId}/acceptFriendRequest", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendshipDTO> acceptFriendshipRequest(@RequestBody Long requestId, @PathVariable Long guestId)
    {
        FriendshipDTO friendshipDTO = friendshipService.acceptRequest(requestId, guestId);
        if (friendshipDTO == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(friendshipDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/declineFriendRequest", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendshipDTO> declineFriendshipRequest(@RequestBody Long requestId, @PathVariable Long guestId)
    {
        FriendshipDTO friendshipDTO = friendshipService.declineRequest(requestId, guestId);
        return new ResponseEntity<>(friendshipDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/allFriends", method = RequestMethod.GET)
    public ResponseEntity<Set<UserDTO>> allFriends(@PathVariable Long guestId)
    {
        Set<UserDTO> retSet = friendshipService.getFriends(guestId);
        return new ResponseEntity<>(retSet, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/unfriendUser/{friendId}", method = RequestMethod.PUT)
    public ResponseEntity<FriendshipDTO> unfriendUser(@PathVariable Long guestId, @PathVariable Long friendId)
    {
        FriendshipDTO friendshipDTO = friendshipService.unfriendUser(guestId, friendId);
        if (friendshipDTO == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(friendshipDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/searchForFriends/{stringParam}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> searchForFriends(@PathVariable Long guestId, @PathVariable String stringParam)
    {
        List<UserDTO> retSet = friendshipService.searchAllGuests(stringParam);
        return new ResponseEntity<>(retSet, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/searchMyFriends/{stringParam}", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> searchMyFriends(@PathVariable Long guestId, @PathVariable String stringParam)
    {
        List<UserDTO> retSet = friendshipService.searchUserFriends(stringParam);
        return new ResponseEntity<>(retSet, HttpStatus.OK);
    }
}
