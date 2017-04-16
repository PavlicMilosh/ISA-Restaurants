package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Barman;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.SystemManager;
import com.isa.restaurant.domain.User;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Milos on 10-Apr-17.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDTO addSystemManager(SystemManager systemManager)
    {
        SystemManager sm = (SystemManager) userRepository.findByUsername(systemManager.getUsername());
        if(sm != null)
            return null;
        User saved = userRepository.save(systemManager);
        return new UserDTO(saved);
    }


    public UserDTO addBarman(Barman barman)
    {
        Barman br = (Barman) userRepository.findByUsername(barman.getUsername());
        if(br != null)
            return null;
        User saved = userRepository.save(barman);
        return new UserDTO(saved);
    }


    public UserDTO changeBarman(Barman barman)
    {
        Barman br = (Barman) userRepository.findById(barman.getId());
        if(br == null)
            return null;
        User saved = userRepository.save(barman);
        return new UserDTO(saved);
    }


    public UserDTO addGuest(Guest guest)
    {
        Guest g = (Guest) userRepository.findByUsername(guest.getUsername());
        if(g != null)
            return null;
        User saved = userRepository.save(guest);
        return new UserDTO(saved);
    }


    public UserDTO updateGuest(Guest guest)
    {

        Guest g = (Guest) userRepository.findById(guest.getId());
        if(g == null)
            return null;
        User saved = userRepository.save(guest);
        return new UserDTO(saved);
    }



}
