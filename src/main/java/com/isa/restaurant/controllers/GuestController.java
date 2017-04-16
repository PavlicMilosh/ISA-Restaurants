package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.DTO.FriendshipDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.services.implementation.FriendshipServiceImpl;
import com.isa.restaurant.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Q on 15-Apr-17.
 */
@RestController
@RequestMapping(value = "/guest")
public class GuestController
{
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private FriendshipServiceImpl friendshipService;


    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerGuest(@RequestBody Guest guest)
    {
        UserDTO saved = userService.addGuest(guest);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{guestId}/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateGuest(@RequestBody Guest guest, @PathVariable Long guestId)
    {
        guest.setId(guestId);
        UserDTO saved = userService.updateGuest(guest);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/sendFriendshipRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendshipDTO> sendFriendshipRequest(@RequestBody Long toWhom, @PathVariable Long guestId)
    {
        FriendshipDTO saved = friendshipService.sendRequest(guestId, toWhom);
        if (saved == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{guestId}/acceptFriendshipRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> acceptFriendshipRequest(@RequestBody Long toWhom, @PathVariable Long guestId)
    {
        return null;
    }


    @RequestMapping(value = "/{guestId}/declineFriendshipRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> declineFriendshipRequest(@RequestBody Long toWhom, @PathVariable Long guestId)
    {
        return null;
    }
}
