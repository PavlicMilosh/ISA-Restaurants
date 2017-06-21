package com.isa.restaurant.domain;

import com.isa.restaurant.domain.DTO.GuestDTO;
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
@AllArgsConstructor(suppressConstructorProperties = true)
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
    private String status;


    public Invitation(Guest to, Reservation reservation)
    {
        this.invited = to;
        this.status = InvitationStatus.PENDING;
        this.reservation = reservation;
    }


    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Invitation)) return false;

        Invitation invitation = (Invitation) o;

        if (id != null ? !id.equals(invitation.id) : false) return false;
        if (reservation != null ? !reservation.equals(invitation.reservation) : invitation.reservation != null) return false;
        if (invited != null ? !invited.equals(invitation.invited) : invitation.invited != null) return false;
        if (status != null ? !status.equals(invitation.status) : invitation.status != null) return false;

        return true;
    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (reservation != null ? reservation.hashCode() : 0);
        result = 31 * result + (invited != null ? invited.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
