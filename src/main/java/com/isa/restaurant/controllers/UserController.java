package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.SystemManager;
import com.isa.restaurant.services.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Milos on 10-Apr-17.
 */
@RestController
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
            return new ResponseEntity(HttpStatus.CONFLICT); //da li moze unprocessable_entity da se vrati u ovom slucaju?
        return new ResponseEntity(saved, HttpStatus.CREATED);
    }
}
