package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.DTO.FriendshipDTO;
import com.isa.restaurant.domain.DTO.GuestAndRelationDTO;
import com.isa.restaurant.domain.DTO.GuestDTO;
import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.repositories.FriendshipRepository;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.search.GuestSearch;
import com.isa.restaurant.services.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class FriendshipServiceImpl implements FriendshipService
{
    private final FriendshipRepository friendshipRepository;
    private final UserRepository userRepository;
    private final GuestSearch guestSearch;

    @Autowired
    public FriendshipServiceImpl(FriendshipRepository friendshipRepository,
                                 UserRepository userRepository,
                                 GuestSearch guestSearch)
    {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
        this.guestSearch = guestSearch;
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
    public FriendshipDTO acceptRequest(Long guestId, Long fromWhomId)
    {
        return updateRequest(guestId, fromWhomId, FriendshipStatus.ACCEPTED);
    }


    @Override
    public FriendshipDTO declineRequest(Long guestId, Long fromWhomId)
    {
        return updateRequest(guestId, fromWhomId, FriendshipStatus.DECLINED);
    }


    private FriendshipDTO updateRequest(Long guestId, Long fromWhomId, String status)
    {
        Friendship friendship = friendshipRepository.findByBothUsers(guestId, fromWhomId);

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
    public FriendshipDTO unfriendUser(Long guestId, Long friendId)
    {
        Friendship friendship = friendshipRepository.findByBothUsers(guestId, friendId);
        Guest actionUser = (Guest)userRepository.findById(guestId);
        if (friendship != null && actionUser != null)
        {
            friendship.setStatus(FriendshipStatus.UNFRIENDED);
            friendship.setActionUser(actionUser);
            friendshipRepository.save(friendship);
            return new FriendshipDTO(friendship);
        }
        return null;
    }


    @Override
    public Set<GuestDTO> getFriends(Long guestId)
    {
        if (userRepository.findById(guestId) == null) return  null;

        Set<GuestDTO> retSet = new HashSet<>();
        Set<Friendship> friendships = friendshipRepository.findAllAcceptedFriendshipsByUserId(guestId);

        for (Friendship f : friendships)
        {
            Guest friend = f.getFriend(guestId);
            retSet.add(new GuestDTO(friend));
        }

        return retSet;
    }


    @Override
    public Set<GuestDTO> getFriendRequests(Long guestId)
    {
        if (userRepository.findById(guestId) == null) return  null;

        Set<GuestDTO> retSet = new HashSet<>();
        Set<Friendship> friendships = friendshipRepository.findAllIncomingFriendRequestsByUserId(guestId);

        for (Friendship f : friendships)
        {
            Guest friend = f.getFriend(guestId);
            retSet.add(new GuestDTO(friend));
        }

        return retSet;
    }


    @Override
    public Set<GuestAndRelationDTO> getAllGuests(Long guestId)
    {
        Set<Guest> guests = userRepository.getAllGuests();
        Set<GuestAndRelationDTO> ret = new HashSet<>();

        for (Guest g : guests)
        {
            Friendship friendship = friendshipRepository.findByBothUsers(guestId, g.getId());

            if (friendship == null)
                ret.add(new GuestAndRelationDTO(g, FriendshipStatus.NONE, null));
            else
                ret.add(new GuestAndRelationDTO(g, friendship.getStatus(), friendship.getActionUser().getId()));
        }

        return ret;
    }


    @Override
    public List<GuestDTO> searchAllGuests(String stringParam)
    {
        List<GuestDTO> ret = new ArrayList<GuestDTO>();
        List<Guest> guests = guestSearch.searchAll(stringParam);
        for (Guest g : guests)
            ret.add(new GuestDTO(g));
        return ret;
    }


    @Override
    public List<GuestDTO> searchUserFriends(String stringParam, Long id)
    {
        String[] s = stringParam.split(" ");
        String s1 = "%" + s[0] + "%";
        String s2 = "%" + s[1] + "%";
        return friendshipRepository.searchAllFriends(s1, s2, id);
    }
}
