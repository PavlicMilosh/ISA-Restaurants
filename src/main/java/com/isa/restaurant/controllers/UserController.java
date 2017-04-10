package com.isa.restaurant.controllers;

import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.SystemManager;
import com.isa.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Milos on 10-Apr-17.
 */
@RestController
@RequestMapping(value = "/users")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register/sysManager")
    public UserDTO registerSysManager(@RequestBody SystemManager systemManager)
    {
        return userService.addSystemManager(systemManager);
    }
}
