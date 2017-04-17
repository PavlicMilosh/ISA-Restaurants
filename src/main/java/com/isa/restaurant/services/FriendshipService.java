package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.FriendshipDTO;
import com.isa.restaurant.domain.DTO.UserDTO;
import com.isa.restaurant.domain.Friendship;

import java.util.Set;

/**
 * Created by Q on 16-Apr-17.
 */
public interface FriendshipService
{
    FriendshipDTO sendRequest(Long from, Long to);

    FriendshipDTO acceptRequest(Long requestId);

    FriendshipDTO declineRequest(Long requestId);

    Set<UserDTO> getFriends(Long guestId);

    Set<UserDTO> getFriendRequests(Long guestId);

}
