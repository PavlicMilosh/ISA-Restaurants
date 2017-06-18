package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.ulitity.Utilities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Q on 16-Jun-17.
 */
@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
public class ReservationWithOrdersDTO
{
    private Long id;
    private RestaurantDTO restaurant;
    private String startDate;
    private String startTime;
    private Long duration;
    private GuestDTO reserver;
    private Set<GuestWithInvitationDTO> invites;
    private Set<OrderDTO> orders;
    private Set<RestaurantTableDTO> tables;


    public ReservationWithOrdersDTO()
    {
        this.invites = new HashSet<>();
        this.orders = new HashSet<>();
        this.tables = new HashSet<>();
    }

    public ReservationWithOrdersDTO(Reservation reservation)
    {
        this();

        this.id = reservation.getId();
        this.restaurant = new RestaurantDTO(reservation.getRestaurant());

        DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfTime = new SimpleDateFormat("HH:mm");
        this.startDate = dfDate.format(reservation.getDateTimeStart());
        this.startTime = dfTime.format(reservation.getDateTimeStart());
        this.duration = Utilities.diffDatesToMinutes(reservation.getDateTimeStart(), reservation.getDateTimeEnd());

        this.reserver = new GuestDTO(reservation.getReserver());

        for (Invitation invitation : reservation.getInvitations())
            this.invites.add(new GuestWithInvitationDTO(invitation.getInvited(), invitation.getStatus()));

        for (Order order : reservation.getOrders())
            this.orders.add(new OrderDTO(order));

        for (RestaurantTable rt : reservation.getTables())
        {
            this.tables.add(new RestaurantTableDTO(rt, false));
        }

    }
}
