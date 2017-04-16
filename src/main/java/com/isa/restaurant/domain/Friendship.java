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
    private FriendshipStatus status;


    public Friendship(Guest firstUser, Guest secondUser)
    {
        this.firstUser = firstUser;
        this.secondUser = secondUser;
    }

}
