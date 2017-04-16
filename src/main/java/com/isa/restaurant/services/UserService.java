package com.isa.restaurant.services;

import com.isa.restaurant.domain.Barman;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.SystemManager;
import com.isa.restaurant.domain.User;

/**
 * Created by Milos on 15-Apr-17.
 */
public interface UserService
{
    public UserDTO addSystemManager(SystemManager systemManager);

    public UserDTO addGuest(Guest guest);

    public UserDTO updateGuest(Guest guest);


}
