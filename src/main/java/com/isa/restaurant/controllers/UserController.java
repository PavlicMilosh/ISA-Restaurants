package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Milos on 10-Apr-17.
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/users")
public class UserController
{
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/register/sysManager", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerSysManager(@RequestBody SystemManager systemManager)
    {
        UserDTO saved = userService.addSystemManager(systemManager);
        if(saved == null)
            return new ResponseEntity(HttpStatus.CONFLICT);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/register/barman", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerBarman(@RequestBody Barman barman)
    {
        UserDTO saved = userService.addBarman(barman);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/register/cook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerCook(@RequestBody Cook cook)
    {
        UserDTO saved = userService.addCook(cook);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/register/bartender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> registerBartender(@RequestBody Bartender bartender)
    {
        UserDTO saved = userService.addBartender(bartender);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update/barman", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeBarman(@RequestBody Barman barman){
        UserDTO saved = userService.changeBarman(barman);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/cook", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeCook(@RequestBody Cook cook){
        UserDTO saved = userService.changeCook(cook);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.OK);
    }

    @RequestMapping(value = "/update/bartender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> changeCook(@RequestBody Bartender bartender){
        UserDTO saved = userService.changeBartender(bartender);
        if(saved == null)
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity(saved, HttpStatus.OK);
    }
}
