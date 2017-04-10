package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.SystemManager;
import com.isa.restaurant.domain.User;
import com.isa.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Milos on 10-Apr-17.
 */
@Service
public class UserService
{
    @Autowired
    UserRepository userRepository;

    public UserDTO addSystemManager(SystemManager systemManager)
    {
        SystemManager sm = (SystemManager) userRepository.findByUsername(systemManager.getUsername());
        if(sm != null)
            return null;
        User saved = userRepository.save(systemManager);
        return new UserDTO(saved);
    }
}
