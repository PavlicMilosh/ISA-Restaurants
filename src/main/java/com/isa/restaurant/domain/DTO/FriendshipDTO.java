package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Friendship;
import com.isa.restaurant.domain.FriendshipStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Q on 16-Apr-17.
 */
@Getter
@Setter
@NoArgsConstructor
public class FriendshipDTO
{
    private Long id;

    private Long firstUserId;
    private Long secondUserId;
    private Long actionUserId;

    private String firstUserUsername;
    private String secondUserUsername;
    private String actionUserUsername;

    private String status;


    public FriendshipDTO(Friendship friendship)
    {
        this.id = friendship.getId();
        this.firstUserId = friendship.getFirstUser().getId();
        this.firstUserUsername = friendship.getFirstUser().getUsername();
        this.secondUserId = friendship.getSecondUser().getId();
        this.secondUserUsername = friendship.getSecondUser().getUsername();
        this.actionUserId = friendship.getActionUser().getId();
        this.actionUserUsername = friendship.getActionUser().getUsername();
        this.status = friendship.getStatus();
    }

}
