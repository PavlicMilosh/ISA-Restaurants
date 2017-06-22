package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;
import com.isa.restaurant.services.UserService;
import com.isa.restaurant.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Milos on 10-Apr-17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @Autowired
    private WorkScheduleService workScheduleService;

    @RequestMapping(value = "/register/sysManager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerSysManager(@RequestBody SystemManager systemManager)
    {
        systemManager.setAuthorities(Role.SYSTEM_MANAGER);
        UserDTO saved = userService.addSystemManager(systemManager);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/register/provider", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerProvider(@RequestBody Provider provider)
    {
        UserDTO saved = userService.addProvider(provider);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{providerId}/updateProvider", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateProvider(@PathVariable Long providerId, @RequestBody Provider provider)
    {
        UserDTO saved = userService.updateProvider(providerId, provider);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/addSchedule", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSchedule(@PathVariable Long userId, @RequestBody List<WorkScheduleDTO> workSchedule)
    {
        boolean done = this.workScheduleService.addWorkSchedule(userId, workSchedule);
        if(!done)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/waiter", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeWaiter(@RequestBody Waiter waiter){
        UserDTO saved = userService.changeWaiter(waiter);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/cook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeCook(@RequestBody Cook cook){
        UserDTO saved = userService.changeCook(cook);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changePassword(@RequestBody Bartender bartender){
        UserDTO saved = userService.changeBartender(bartender);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/getSchedule", method = RequestMethod.GET) //+++
    public ResponseEntity<Set<WorkScheduleDTO>> getWaiterSchedule(@PathVariable Long id){
        Set<WorkScheduleDTO> schedule=userService.getSchedule(id);
        if(schedule == null)
        {
            schedule = new HashSet<>();
        }
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/getRestaurant", method = RequestMethod.GET)
    public ResponseEntity<RestaurantDTO> getUserRestaurant(@PathVariable Long id){
        RestaurantDTO r=userService.getUserRestaurant(id);
        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/getRestaurantOrders", method = RequestMethod.GET)
    public ResponseEntity<Set<OrderItemDTO>> getRestaurantOrders(@PathVariable Long id) {
        Set<OrderItemDTO> orders = userService.getRestaurantOrders(id);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UpdatingUser> getUserForUpdate(@PathVariable Long userId)
    {
        UpdatingUser u = userService.findForUpdate(userId);
        if(u == null)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @RequestMapping(value = "/{userId}/getWaiterRegionId", method = RequestMethod.GET)
    public ResponseEntity<Long> getWaiterRegionId(@PathVariable Long userId)
    {
        Long regionId = userService.getWaiterRegionId(userId);
        return new ResponseEntity<>(regionId, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/getAllSchedule", method = RequestMethod.GET) //+++
    public ResponseEntity<Set<WorkScheduleDTO>> getAllSchedule(@PathVariable Long id){
        Set<WorkScheduleDTO> schedule=userService.getAllSchedule(id);
        if(schedule == null)
        {
            schedule = new HashSet<>();
        }
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }
}
