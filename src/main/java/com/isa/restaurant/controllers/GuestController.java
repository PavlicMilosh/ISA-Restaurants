package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.Invitation;
import com.isa.restaurant.domain.RestaurantTable;
import com.isa.restaurant.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by Q on 15-Apr-17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/guest")
public class GuestController
{
    private final UserService userService;
    private final FriendshipService friendshipService;
    private final ReservationService reservationService;
    private final VerificationTokenService verificationTokenService;
    private final MailService mailService;
    private final RestaurantService restaurantService;

    @Autowired
    public GuestController(FriendshipService friendshipService,
                           UserService userService,
                           ReservationService reservationService,
                           VerificationTokenService verificationTokenService,
                           MailService mailService,
                           RestaurantService restaurantService)
    {
        this.reservationService = reservationService;
        this.verificationTokenService = verificationTokenService;
        this.friendshipService = friendshipService;
        this.userService = userService;
        this.mailService = mailService;
        this.restaurantService = restaurantService;
    }


    @RequestMapping(value = "/{guestId}/update",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody Guest guest, @PathVariable Long guestId)
    {
        guest.setId(guestId);
        GuestDTO saved = userService.updateGuest(guest);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }


    @RequestMapping(value = "/register",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerGuest(@RequestBody Guest guest)
    {
        UserDTO saved = userService.addGuest(guest);

        if(saved == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);

        //TODO: OVO KADA SE PONOVO UVEDE MAIL OTKOMENTARISATI
        //String token = verificationTokenService.getTokenByUserId(guest.getId());
        //mailService.sendUserActivationEmail(guest, token);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{guestId}/activation/{verificationTokenValue}",
                    method = RequestMethod.GET)
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


    @RequestMapping(value = "/{guestId}/sendFriendRequest/{toWhomId}",
                    method = RequestMethod.POST,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendshipDTO> sendFriendshipRequest(@PathVariable Long guestId, @PathVariable Long toWhomId)
    {
        FriendshipDTO saved = friendshipService.sendRequest(guestId, toWhomId);
        if (saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{guestId}/acceptFriendRequest/{fromWhomId}",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendshipDTO> acceptFriendshipRequest(@PathVariable Long guestId, @PathVariable Long fromWhomId)
    {
        FriendshipDTO friendshipDTO = friendshipService.acceptRequest(guestId, fromWhomId);
        if (friendshipDTO == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(friendshipDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/declineFriendRequest/{fromWhomId}",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendshipDTO> declineFriendshipRequest(@PathVariable Long guestId, @PathVariable Long fromWhomId)
    {
        FriendshipDTO friendshipDTO = friendshipService.declineRequest(guestId, fromWhomId);
        if (friendshipDTO == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(friendshipDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/unfriendUser/{friendId}",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FriendshipDTO> unfriendUser(@PathVariable Long guestId, @PathVariable Long friendId)
    {
        FriendshipDTO friendshipDTO = friendshipService.unfriendUser(guestId, friendId);
        if (friendshipDTO == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(friendshipDTO, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/getFriendRequests",
                    method = RequestMethod.GET)
    public ResponseEntity<Set<GuestDTO>> getFriendRequests(@PathVariable Long guestId)
    {
        Set<GuestDTO> requests = friendshipService.getFriendRequests(guestId);
        if (requests == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/getFriends",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<GuestDTO>> getFriends(@PathVariable Long guestId)
    {
        Set<GuestDTO> requests = friendshipService.getFriends(guestId);
        if (requests == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/getAllGuests",
                    method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<GuestAndRelationDTO>> getAllGuests(@PathVariable Long guestId)
    {
        Set<GuestAndRelationDTO> ret = friendshipService.getAllGuests(guestId);
        if (ret == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/searchForFriends",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GuestAndRelationDTO>> searchForFriends(@PathVariable Long guestId, @RequestBody String stringParam)
    {
        List<GuestAndRelationDTO> retSet = friendshipService.searchAllGuests(stringParam, guestId);
        return new ResponseEntity<>(retSet, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/searchMyFriends",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GuestAndRelationDTO>> searchMyFriends(@PathVariable Long guestId, @RequestBody String stringParam)
    {
        List<GuestAndRelationDTO> retSet = friendshipService.searchUserFriends(stringParam, guestId);
        return new ResponseEntity<>(retSet, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/sendReservation",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> sendReservation(@PathVariable Long guestId, @RequestBody ReservationDTO reservationDTO)
    {
        ReservationDTO ret = reservationService.addReservation(guestId, reservationDTO);
        if (ret == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/searchRestaurants",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantDTO>> searchRestaurants(@PathVariable Long guestId, @RequestBody String searchParam)
    {
        List<RestaurantDTO> ret = restaurantService.searchRestaurantsByNameAndDescription(searchParam);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }


    /*@RequestMapping(value = "/{guestId}/sendInvitation",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> sendInvitation(@PathVariable Long guestId, @RequestBody Invitation invitation)
    {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }*/


    @RequestMapping(value = "/{guestId}/getTables",
                    method = RequestMethod.PUT,
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RestaurantTableDTO>> getTables(@PathVariable Long guestId, @RequestBody ReservationDTO reservationDTO)
    {
        List<RestaurantTableDTO> ret = this.reservationService.getTables(guestId, reservationDTO);
        if (ret == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(ret, HttpStatus.OK);
    }


    @RequestMapping(value = "/{guestId}/updateReservation/{reservationId}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long guestId, @PathVariable Long reservationId)
    {
        return null;
    }


    @RequestMapping(value = "/{guestId}/acceptInvitation/{verificationTokenValue}",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReservationDTO> acceptInvitation(@PathVariable Long guestId, @PathVariable String verificationTokenValue)
    {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
    }
}
