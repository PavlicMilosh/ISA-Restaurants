package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.DTO.WorkScheduleDTO;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/register/sysManager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerSysManager(@RequestBody SystemManager systemManager)
    {
        systemManager.setPassword(passwordEncoder.encode(systemManager.getPassword()));
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

    @RequestMapping(value = "/{userId}/addSchedule", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addSchedule(@PathVariable Long userId, @RequestBody List<WorkScheduleDTO> workSchedule)
    {
        boolean done = this.workScheduleService.addWorkSchedule(userId, workSchedule);
        if(done == false)
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

    @RequestMapping(value = "/update/bartender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeCook(@RequestBody Bartender bartender){
        UserDTO saved = userService.changeBartender(bartender);
        if(saved == null)
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/waiter/{id}/getSchedule", method = RequestMethod.GET)
    public ResponseEntity<Set<WorkSchedule>> getWaiterSchedule(@PathVariable Long id){
        Set<WorkSchedule> schedule=userService.getWaiterSchedule(id);
        if(schedule==null){
            schedule=new HashSet<>();
        }
        return new ResponseEntity(schedule, HttpStatus.OK);
    }

    @RequestMapping(value = "/cook/{id}/getSchedule", method = RequestMethod.GET)
    public ResponseEntity<Set<WorkSchedule>> getCookSchedule(@PathVariable Long id){
        Set<WorkSchedule> schedule=userService.getCookSchedule(id);
        if(schedule==null){
            schedule=new HashSet<>();
        }
        return new ResponseEntity(schedule, HttpStatus.OK);
    }

    @RequestMapping(value = "/bartender/{id}/getSchedule", method = RequestMethod.GET)
    public ResponseEntity<Set<WorkSchedule>> getBartenderSchedule(@PathVariable Long id){
        Set<WorkSchedule> schedule=userService.getBartenderSchedule(id);
        if(schedule==null){
            schedule=new HashSet<>();
        }
        return new ResponseEntity(schedule, HttpStatus.OK);
    }
}
