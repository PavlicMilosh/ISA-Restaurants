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

import java.util.HashSet;
import java.util.Set;

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


    // USER RELATED (for all user subclasses)

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


    // GUEST RELATED

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
        g.setFirstName(guest.getFirstName());
        g.setLastName(guest.getLastName());
        g.setEmail(guest.getEmail());
        g.setPassword(guest.getPassword());
        Guest saved = (Guest)userRepository.save(g);
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
    public UserDTO addProvider(Provider provider)
    {
        Provider p = (Provider) userRepository.findByEmail(provider.getEmail());
        if(p != null)
            return null;
        User saved = userRepository.save(provider);
        return new UserDTO(saved);
    }
    @Override
    public UserDTO changeWaiter(Waiter waiter)
    {
        Waiter w = (Waiter) userRepository.findByEmail(waiter.getEmail());
        if (w != null)
            return null;
        User saved = userRepository.save(w);
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

    @Override
    public Set<WorkSchedule> getWaiterSchedule(Long id)
    {
        Set<WorkSchedule> schedule=null;
        Waiter w=(Waiter) userRepository.findById(id);
        if(w == null)
            return schedule;
        schedule=w.getSchedule();
        return schedule;
    }

    @Override
    public Set<WorkSchedule> getCookSchedule(Long id)
    {
        Set<WorkSchedule> schedule=null;
        Cook w=(Cook) userRepository.findById(id);
        if(w == null)
            return schedule;
        schedule=w.getSchedule();
        return schedule;
    }

    @Override
    public Set<WorkSchedule> getBartenderSchedule(Long id)
    {
        Set<WorkSchedule> schedule=null;
        Bartender w=(Bartender) userRepository.findById(id);
        if(w == null)
            return schedule;
        schedule=w.getSchedule();
        return schedule;
    }
}
