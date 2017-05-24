package com.isa.restaurant.services;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.GuestDTO;
import com.isa.restaurant.domain.DTO.UserDTO;

import java.util.Set;

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
    UserDTO addProvider(Provider provider);

    UserDTO changeWaiter(Waiter waiter);
    UserDTO changeCook(Cook cook);
    UserDTO changeBartender(Bartender bartender);
    Set<WorkSchedule> getWaiterSchedule(Long id);
    Set<WorkSchedule> getCookSchedule(Long id);
    Set<WorkSchedule> getBartenderSchedule(Long id);
}
