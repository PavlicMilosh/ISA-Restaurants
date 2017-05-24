package com.isa.restaurant.domain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by Q on 13-May-17.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "reservation_id")
    private Long id;

    @OneToOne
    @JoinColumn(referencedColumnName = "restaurant_id", name = "reservation_restaurant_id")
    private Restaurant restaurant;

    @Column(name = "reservation_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeStart;

    @Column(name = "reservation_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTimeEnd;

    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id", name = "reservation_reserver_id")
    private Guest reserver;

    @OneToMany
    private Set<Invitation> invitations;

    @OneToMany
    private Set<RestaurantTable> tables;

    @OneToMany
    private Set<Order> orders;


    @Override
    public boolean equals(Object other)
    {
        if (this == other) return true;
        if (!(other instanceof Reservation)) return false;

        Reservation reservation = (Reservation) other;

        if (id != null && !id.equals(reservation.id)) return false;
        if (restaurant != null ? !restaurant.equals(reservation.restaurant) : reservation.restaurant != null) return false;
        if (dateTimeStart != null ? !dateTimeStart.equals(reservation.dateTimeStart) : reservation.dateTimeStart != null) return false;
        if (dateTimeEnd != null ? !dateTimeEnd.equals(reservation.dateTimeEnd) : reservation.dateTimeEnd != null) return false;
        if (reserver != null ? !reserver.equals(reservation.reserver) : reservation.reserver != null) return false;

        return true;

    }


    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + (restaurant != null ? restaurant.hashCode() : 0);
        result = 31 * result + (dateTimeStart != null ? dateTimeStart.hashCode() : 0);
        result = 31 * result + (dateTimeEnd != null ? dateTimeEnd.hashCode() : 0);
        result = 31 * result + (reserver != null ? reserver.hashCode() : 0);
        return result;
    }


    public Boolean hasTable(Long tableId)
    {
        for (RestaurantTable rt : tables)
            if (rt.getId().longValue() == tableId.longValue())
                return true;
        return false;
    }
}
