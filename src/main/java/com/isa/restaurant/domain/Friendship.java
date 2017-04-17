package com.isa.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Q on 15-Apr-17.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "friendship",
       uniqueConstraints = @UniqueConstraint(columnNames={"friendship_first_user", "friendship_second_user"}))
public class Friendship
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "friendship_id")
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "friendship_first_user")
    private Guest firstUser;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "friendship_second_user")
    private Guest secondUser;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "friendship_last_action_user")
    private Guest actionUser;

    @Column(name = "friendship_status")
    private String status;


    public Friendship(Guest firstUser, Guest secondUser)
    {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }


    public Guest getFriend(Long guestId)
    {
        if (firstUser.getId().longValue() != guestId.longValue())
            return firstUser;
        else if (secondUser.getId().longValue() != guestId.longValue())
            return secondUser;
        return null;
    }


    public Boolean containsGuest(Long guestId)
    {
        if (firstUser.getId().longValue() == guestId.longValue() ||
            secondUser.getId().longValue() == guestId.longValue())
            return true;
        return false;
    }


    public Boolean isActionUser(Long guestId)
    {
        return actionUser.getId().longValue() == guestId.longValue();
    }

}
