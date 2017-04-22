package com.isa.restaurant.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


@Entity
@Table(name = "verification_token")
@Getter
@Setter
@NoArgsConstructor
public class VerificationToken
{
    @Id
    @GeneratedValue
    @Column(name = "verification_token_id")
    private Long id;

    @Column(name = "verification_token_string")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "verification_token_user_id")
    private Guest guest;

    @Temporal(TemporalType.DATE)
    @Column(name = "verification_token_expiry_date")
    private Date expiryDate;


    public VerificationToken(Guest guest, Integer minutes)
    {
        this.expiryDate = calculateExpiryDate(minutes);
        this.guest = guest;
        this.token = UUID.randomUUID().toString();
    }


    private Date calculateExpiryDate(int expiryTimeInMinutes)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }


    public Boolean isOwner(Long userId)
    {
        return guest.getId().longValue() == userId.longValue();
    }
}
