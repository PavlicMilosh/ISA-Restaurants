package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import com.isa.restaurant.domain.Guest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Q on 07-May-17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestAndRelationDTO
{
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean enabled;
    private String friendshipStatus;
    private Long lastActionUserId;

    public GuestAndRelationDTO(Guest other, String friendshipStatus, Long lastActionUserId)
    {
        this.id = other.getId();
        this.firstName = other.getFirstName();
        this.lastName = other.getLastName();
        this.email = other.getEmail();
        this.enabled = other.getEnabled();
        this.friendshipStatus = friendshipStatus;
        this.lastActionUserId = lastActionUserId;
    }
}
