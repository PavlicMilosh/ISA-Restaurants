package com.isa.restaurant.domain.DTO;
import com.isa.restaurant.domain.*;
import com.isa.restaurant.ulitity.Utilities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Q on 13-May-17.
 */
@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
public class ReservationDTO
{
    private Long id;
    private RestaurantDTO restaurant;
    private String startDate;
    private String startTime;
    private Long duration;
    private GuestDTO reserver;
    private Set<RestaurantTableDTO> tables;
    private Set<GuestDTO> invites;
    private Set<DrinkOrderDTO> drinkOrders;
    private Set<DishOrderDTO> dishOrders;


    public ReservationDTO()
    {
        this.tables = new HashSet<>();
        this.invites = new HashSet<>();
        this.drinkOrders = new HashSet<>();
        this.dishOrders = new HashSet<>();
    }

    public ReservationDTO(Reservation reservation)
    {
        this();

        DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfTime = new SimpleDateFormat("HH:mm");

        this.id = reservation.getId();

        this.restaurant = new RestaurantDTO(reservation.getRestaurant());
        this.startDate = dfDate.format(reservation.getDateTimeStart());
        this.startTime = dfTime.format(reservation.getDateTimeStart());
        this.duration = Utilities.diffDatesToMinutes(reservation.getDateTimeStart(), reservation.getDateTimeEnd());
        this.reserver = new GuestDTO(reservation.getReserver());

        for (RestaurantTable rt : reservation.getTables())
            this.tables.add(new RestaurantTableDTO(rt, false));

        for (Invitation invitation : reservation.getInvitations())
            this.invites.add(new GuestDTO(invitation.getInvited()));

        for (Order o : reservation.getOrders())
        {
            for (OrderItem oi : o.getOrderItems())
            {
                if (oi.getIsDish())
                    this.dishOrders.add(new DishOrderDTO(oi.getDish(), oi.getNumber()));
                else
                    this.drinkOrders.add(new DrinkOrderDTO(oi.getDrink(), oi.getNumber()));
            }
        }
        //        for (Guest g : reservation.getInvites())
//            this.invites.add(new GuestDTO(g));
    }
}
