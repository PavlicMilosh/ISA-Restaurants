package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.DTO.FriendshipDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.services.implementation.FriendshipServiceImpl;
import com.isa.restaurant.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Q on 15-Apr-17.
 */
@RestController
@RequestMapping(value = "/guest")
public class GuestController
{
    private final UserServiceImpl userService;

    private final FriendshipServiceImpl friendshipService;

    @Autowired
    public GuestController(FriendshipServiceImpl friendshipService, UserServiceImpl userService)
    {
        this.friendshipService = friendshipService;
        this.userService = userService;
    }


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
        List<UserDTO> retSet = friendshipService.searchForUsers(stringParam);
        return new ResponseEntity<>(retSet, HttpStatus.OK);
    }
}
