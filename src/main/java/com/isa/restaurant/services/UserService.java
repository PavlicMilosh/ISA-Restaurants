package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.GuestDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.SystemManager;

/**
 * Created by Milos on 15-Apr-17.
 */
public interface UserService
{
    UserDTO addSystemManager(SystemManager systemManager);
    UserDTO addGuest(Guest guest);
    GuestDTO updateGuest(Guest guest);
    UserDTO findById(Long id);
    UserDTO findByEmail(String email);
}
