package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.GuestDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.repositories.VerificationTokenRepository;
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
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final Integer verificationTokenExpiryTime = 1440;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           VerificationTokenRepository verificationTokenRepository)
    {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }


    @Override
    public UserDTO findByEmail(String email)
    {
        return new UserDTO(userRepository.findByEmail(email));
    }


    @Override
    public UserDTO findById(Long id)
    {
        User user = userRepository.findById(id);
        if (user == null) return null;
        return new UserDTO(user);
    }


    @Override
    public UserDTO addGuest(Guest guest)
    {
        Guest g = (Guest) userRepository.findByEmail(guest.getEmail());
        if(g != null)
            return null;
        guest.setEnabled(false);
        User saved = userRepository.save(guest);
        VerificationToken verificationToken = new VerificationToken(guest, this.verificationTokenExpiryTime);
        verificationTokenRepository.save(verificationToken);
        return new UserDTO(saved);
    }

    @Override
    public GuestDTO updateGuest(Guest guest)
    {
        Guest g = (Guest) userRepository.findById(guest.getId());
        if(g == null)
            return null;
        Guest saved = (Guest)userRepository.save(guest);
        return new GuestDTO(saved);
    }

    @Override
    public UserDTO addSystemManager(SystemManager systemManager)
    {
        SystemManager sm = (SystemManager) userRepository.findByEmail(systemManager.getEmail());
        if(sm != null)
            return null;
        User saved = userRepository.save(systemManager);
        return new UserDTO(saved);
    }

    @Override
    public UserDTO changeWaiter(Waiter waiter)
    {
        Waiter br = (Waiter) userRepository.findById(waiter.getId());
        if(br == null)
            return null;
        User saved = userRepository.save(waiter);
        return new UserDTO(saved);
    }

    @Override
    public UserDTO changeCook(Cook cook)
    {
        Cook ck = (Cook) userRepository.findById(cook.getId());
        if(ck == null)
            return null;
        User saved = userRepository.save(cook);
        return new UserDTO(saved);
    }

    @Override
    public UserDTO changeBartender(Bartender bartender)
    {
        Bartender br = (Bartender) userRepository.findById(bartender.getId());
        if(br == null)
            return null;
        User saved = userRepository.save(bartender);
        return new UserDTO(saved);
    }
}
