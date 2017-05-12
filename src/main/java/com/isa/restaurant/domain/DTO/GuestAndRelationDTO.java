package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.User;
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


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof GuestAndRelationDTO)) return false;

        GuestAndRelationDTO gar = (GuestAndRelationDTO) o;

        if (id != null && !id.equals(gar.id)) return false;
        if (email != null ? !email.equals(gar.email) : gar.email != null) return false;
        if (firstName != null ? !firstName.equals(gar.firstName) : gar.firstName != null) return false;
        if (lastName != null ? !lastName.equals(gar.lastName) : gar.lastName == null) return false;
        if (enabled != null ? !enabled.equals(gar.enabled) : gar.enabled == null) return false;
        if (friendshipStatus != null ? !friendshipStatus.equals(gar.friendshipStatus) : gar.friendshipStatus == null) return false;

        if (lastActionUserId == null && gar.lastActionUserId == null) return true;
        else return lastActionUserId == gar.lastActionUserId;

    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (friendshipStatus != null ? friendshipStatus.hashCode() : 0);
        result = 31 * result + (lastActionUserId != null ? lastActionUserId.hashCode() : 0);
        return result;
    }
}

