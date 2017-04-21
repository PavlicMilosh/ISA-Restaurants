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

    private String firstUserEmail;
    private String secondUserEmail;
    private String actionUserEmail;

    private String status;


    public FriendshipDTO(Friendship friendship)
    {
        this.id = friendship.getId();
        this.firstUserId = friendship.getFirstUser().getId();
        this.firstUserEmail = friendship.getFirstUser().getEmail();
        this.secondUserId = friendship.getSecondUser().getId();
        this.secondUserEmail = friendship.getSecondUser().getEmail();
        this.actionUserId = friendship.getActionUser().getId();
        this.actionUserEmail = friendship.getActionUser().getEmail();
        this.status = friendship.getStatus();
    }

}
