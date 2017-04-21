package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.User;
import com.isa.restaurant.domain.Barman;
import com.isa.restaurant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by djuro on 4/13/2017.
 */
@Service
public class BarmanService
{

    @Autowired
    UserRepository userRepository;

    public UserDTO changeFirstName(String firstName, String email)
    {
        Barman barman = (Barman) userRepository.findByEmail(email);
        if(barman == null)
            return null;

        barman.setFirstName(firstName);
        User saved = userRepository.save(barman);
        return new UserDTO(saved);
    }

    public UserDTO changeLastName(String lastName, String email)
    {
        Barman barman = (Barman) userRepository.findByEmail(email);
        if(barman == null)
            return null;

        barman.setLastName(lastName);
        User saved = userRepository.save(barman);
        return new UserDTO(saved);
    }

    public UserDTO changeUsername(String newEmail, String email)
    {
        Barman barman = (Barman) userRepository.findByEmail(email);
        if(barman == null)
            return null;

        barman.setEmail(newEmail);
        User saved = userRepository.save(barman);
        return new UserDTO(saved);

    }

    public UserDTO changePassword(String newPassword, String oldPassword, String email)
    {
        Barman barman = (Barman) userRepository.findByEmail(email);
        if(barman == null)
            return null;

        if(barman.getPassword().equals(oldPassword)){
            barman.setPassword(newPassword);
        }
        User saved = userRepository.save(barman);

        return new UserDTO(saved);

    }

}
