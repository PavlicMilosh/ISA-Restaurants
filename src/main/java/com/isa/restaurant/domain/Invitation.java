package com.isa.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Q on 13-May-17.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invitation")
public class Invitation
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "invitation_id")
    private Long id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "reservation_id", name = "invitation_reservation_id")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "invitation_invited_user_id")
    private Guest invited;

    @Column(name = "invitation_status")
    private String invitationStatus;
}
