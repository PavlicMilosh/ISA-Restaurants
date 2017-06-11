package com.isa.restaurant.domain;
import com.isa.restaurant.domain.DTO.ReservationDTO;
import com.isa.restaurant.domain.DTO.RestaurantTableDTO;
import com.isa.restaurant.ulitity.Utilities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Q on 13-May-17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
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

    @Column(name = "reservation_status")
    private String status;

    @OneToMany
    private Set<Invitation> invitations;

    @OneToMany
    private Set<RestaurantTable> tables;

    @OneToOne
    @JoinColumn(referencedColumnName = "order_id", name = "reservation_order_id")
    private Order order;


    public Reservation()
    {
        this.invitations = new HashSet<>();
        this.tables = new HashSet<>();
    }


    public Reservation(ReservationDTO reservationDTO)
    {
        this.tables = new HashSet<>();
        this.invitations = new HashSet<>();

        this.restaurant = new Restaurant(reservationDTO.getRestaurant());
        this.reserver = new Guest(reservationDTO.getReserver());

        this.dateTimeStart = Utilities.createDateFromString(reservationDTO.getStartDate(), reservationDTO.getStartTime());
        this.dateTimeEnd = Utilities.addMinutesToDate(this.dateTimeStart, reservationDTO.getDuration());

        for (RestaurantTableDTO rtDTO : reservationDTO.getTables())
            this.tables.add(new RestaurantTable(rtDTO));
    }


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
        if (order != null ? !order.equals(reservation.order) : reservation.order != null) return false;
        if (status != null ? !status.equals(reservation.status) : reservation.status != null) return false;
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
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }


    public Boolean hasTable(Long tableId)
    {
        for (RestaurantTable rt : tables)
            if (rt.getId().longValue() == tableId.longValue())
                return true;
        return false;
    }


    public void addTable(RestaurantTable table)
    {
        if (!this.tables.contains(table))
            this.tables.add(table);
    }


    public void addInvitation(Invitation invitation)
    {
        this.invitations.add(invitation);
    }


}
