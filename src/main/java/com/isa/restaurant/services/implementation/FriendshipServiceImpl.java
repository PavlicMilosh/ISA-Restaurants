package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.DTO.FriendshipDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.repositories.FriendshipRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Q on 16-Apr-17.
 */
@Service
public class FriendshipServiceImpl implements FriendshipService
{
    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public FriendshipDTO sendRequest(Long from, Long to)
    {
        // treba ubaciti proveru da ne postoji vec takav request, ako postoji da se izmeni status
        Guest gFrom = (Guest) userRepository.findById(from);
        Guest gTo = (Guest) userRepository.findById(to);
        FriendshipDTO friendshipDTO = null;

        if (gFrom != null && gTo != null)
        {
            Friendship friendship = new Friendship(gFrom, gTo);
            friendship.setStatus(FriendshipStatus.PENDING);
            friendship.setActionUser(gFrom);
            friendshipRepository.save(friendship);
            friendshipDTO = new FriendshipDTO(friendship);
        }

        return friendshipDTO;
    }


    @Override
    public FriendshipDTO acceptRequest(Long requestId)
    {
        return updateRequest(requestId, FriendshipStatus.ACCEPTED);
    }


    @Override
    public FriendshipDTO declineRequest(Long requestId)
    {
        return updateRequest(requestId, FriendshipStatus.DECLINED);
    }


    private FriendshipDTO updateRequest(Long requestId, FriendshipStatus status)
    {
        Friendship friendship = friendshipRepository.findById(requestId);
        FriendshipDTO friendshipDTO = null;

        if (friendship != null)
        {
            friendship.setStatus(status);
            friendshipRepository.save(friendship);
            friendshipDTO = new FriendshipDTO(friendship);
        }

        return friendshipDTO;
    }


    @Override
    public Set<UserDTO> getFriends(Long guestId)
    {
        // dobaviti sve friendships gde je status ACCEPTED
        // na osnovu userId-a naci sve ostale id-ove koji odgovaraju
        // dobaviti takve user-e
        // vratiti
        return null;
    }

    @Override
    public Set<UserDTO> getFriendRequests(Long guestId)
    {
        // dobaviti sve friendships gde je status PENDING i gde je actionUser != requestId
        // na osnovu userId-a naci sve ostale id-ove koji odgovaraju
        // dobaviti takve user-e
        // vratiti
        return null;
    }
}
