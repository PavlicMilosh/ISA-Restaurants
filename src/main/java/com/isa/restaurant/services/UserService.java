package com.isa.restaurant.services;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.*;

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
    UserDTO changePassword(UserDTO userDTO);
    Set<WorkScheduleDTO> getSchedule(Long id);
    RestaurantDTO getUserRestaurant(Long id);
    Set<OrderItemDTO> getRestaurantOrders(Long id);
    UserDTO updateProvider(Long providerId, Provider provider);
    UpdatingUser findForUpdate(Long userId);
    Long getWaiterRegionId(Long userId);
    Set<WorkScheduleDTO> getAllSchedule(Long id);
}
