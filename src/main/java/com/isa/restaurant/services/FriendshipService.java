package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.FriendshipDTO;
import com.isa.restaurant.domain.DTO.GuestAndRelationDTO;
import com.isa.restaurant.domain.DTO.GuestDTO;
import com.isa.restaurant.domain.DTO.UserDTO;

import java.util.List;
import java.util.Set;

/**
 * Created by Q on 16-Apr-17.
 */
public interface FriendshipService
{
    FriendshipDTO sendRequest(Long from, Long to);
    FriendshipDTO acceptRequest(Long guestId, Long fromWhomId);
    FriendshipDTO declineRequest(Long requestId, Long guestId);
    FriendshipDTO unfriendUser(Long guestId, Long friendId);

    Set<GuestDTO> getFriends(Long guestId);
    Set<GuestDTO> getFriendRequests(Long guestId);

    Set<GuestAndRelationDTO> getAllGuests(Long guestId);

    List<GuestDTO> searchAllGuests(String stringParam);
    List<GuestDTO> searchUserFriends(String stringParam, Long userId);
}
