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

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class FriendshipServiceImpl implements FriendshipService
{
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;


    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository, UserRepository userRepository)
    {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }


    @Override
    public FriendshipDTO sendRequest(Long from, Long to)
    {
        // send to self
        if (from.longValue() == to.longValue())
            return null;

        Friendship friendship = friendshipRepository.findByBothUsers(from, to);

        if (friendship != null)
        {
            // send to accepted
            if (friendship.getStatus().equalsIgnoreCase(FriendshipStatus.ACCEPTED))
                return null;

                // send to declined or pending (resend)
            else if (friendship.getStatus().equalsIgnoreCase(FriendshipStatus.PENDING )||
                    friendship.getStatus().equalsIgnoreCase(FriendshipStatus.DECLINED))
            {
                friendship.setStatus(FriendshipStatus.PENDING);
                friendshipRepository.save(friendship);
                return new FriendshipDTO(friendship);
            }
        }

        Guest gFrom = (Guest) userRepository.findById(from);
        Guest gTo = (Guest) userRepository.findById(to);

        if (gFrom != null && gTo != null)
        {
            friendship = new Friendship(gFrom, gTo);
            friendship.setStatus(FriendshipStatus.PENDING);
            friendship.setActionUser(gFrom);
            friendshipRepository.save(friendship);
            return new FriendshipDTO(friendship);
        }

        return null;
    }


    @Override
    public FriendshipDTO acceptRequest(Long requestId, Long guestId)
    {
        return updateRequest(requestId, guestId, FriendshipStatus.ACCEPTED);
    }


    @Override
    public FriendshipDTO declineRequest(Long requestId, Long guestId)
    {
        return updateRequest(requestId, guestId, FriendshipStatus.DECLINED);
    }


    private FriendshipDTO updateRequest(Long requestId, Long guestId, String status)
    {
        Friendship friendship = friendshipRepository.findById(requestId);

        if (friendship != null &&
                friendship.containsGuest(guestId) &&
                !friendship.isActionUser(guestId))
        {
            Guest guest = (Guest) userRepository.findById(guestId);
            friendship.setStatus(status);
            friendship.setActionUser(guest);
            friendshipRepository.save(friendship);
            return new FriendshipDTO(friendship);
        }

        return null;
    }


    @Override
    public Set<UserDTO> getFriends(Long guestId)
    {
        Set<UserDTO> retSet = new HashSet<>();
        Set<Friendship> friendships = friendshipRepository.findAllAcceptedFriendshipsByUserId(guestId);

        for (Friendship f : friendships)
        {
            Guest friend = f.getFriend(guestId);
            retSet.add(new UserDTO(friend));
        }

        return retSet;
    }


    @Override
    public Set<FriendshipDTO> getFriendRequests(Long guestId)
    {
        Set<FriendshipDTO> retSet = new HashSet<>();
        Set<Friendship> friendships = friendshipRepository.findAllIncomingFriendRequestsByUserId(guestId);

        for (Friendship f : friendships)
            retSet.add(new FriendshipDTO(f));

        return retSet;
    }


    @Override
    public FriendshipDTO unfriendUser(Long guestId, Long friendId)
    {
        Friendship friendship = friendshipRepository.findByBothUsers(guestId, friendId);
        if (friendship != null)
        {
            friendship.setStatus(FriendshipStatus.UNFRIENDED);
            friendshipRepository.save(friendship);
            return new FriendshipDTO(friendship);
        }
        return null;
    }


    @Override
    public List<UserDTO> searchAllGuests(String stringParam)
    {
        return null;
    }



    @Override
    public List<UserDTO> searchUserFriends(String stringParam)
    {
        return null;
    }
}
