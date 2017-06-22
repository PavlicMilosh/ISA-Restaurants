package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.Reservation;
import com.isa.restaurant.domain.RestaurantMark;
import com.isa.restaurant.ulitity.Utilities;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Q on 17-Jun-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class HistoryDTO
{
    private Long restaurantId;
    private Long reservationId;
    private Long orderId;

    private String restaurantName;
    private String restaurantDescription;

    private Double restaurantMeanMark;
    private Double restaurantFriendsMark;
    private Double restaurantMyMark;

    private Double mealMyMark;
    private Boolean isMark;

    private Double waiterMark;
    private String waiterFirstName;
    private String waiterLastName;
    private Long waiterId;

    private String dateOfVisit;
    private String timeOfVisit;

    public HistoryDTO(Reservation reservation,
                      Double restaurantFriendsMark,
                      Double restaurantMyMark,
                      Double restaurantMeanMark,
                      Long guestId)
    {
        this.reservationId = reservation.getId();
        this.restaurantId = reservation.getRestaurant().getId();

        this.restaurantDescription = reservation.getRestaurant().getDescription();
        this.restaurantName = reservation.getRestaurant().getName();

        for (Order order : reservation.getOrders())
        {
            if (order.getGuest().getId() == guestId)
            {
                this.orderId = order.getId();
                this.mealMyMark = order.getMark();
                break;
            }
        }

        this.restaurantMeanMark = restaurantMeanMark;
        this.restaurantFriendsMark = restaurantFriendsMark;
        this.restaurantMyMark = restaurantMyMark;

        DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfTime = new SimpleDateFormat("HH:mm");

        this.dateOfVisit = dfDate.format(reservation.getDateTimeStart());
        this.timeOfVisit = dfTime.format(reservation.getDateTimeStart());
    }

    public HistoryDTO(Reservation reservation,
                      Double restaurantFriendsMark,
                      Double restaurantMyMark,
                      Double restaurantMeanMark,
                      Long orderId, Double mealMyMark,
                      Boolean isMark, Double waiterMark, String waiterFirstName,
                      String waiterLastName, Long waiterId)
    {
        this.reservationId = reservation.getId();
        this.restaurantId = reservation.getRestaurant().getId();

        this.restaurantDescription = reservation.getRestaurant().getDescription();
        this.restaurantName = reservation.getRestaurant().getName();

        this.orderId=orderId;
        this.mealMyMark=mealMyMark;
        this.isMark=isMark;
        this.waiterMark=waiterMark;
        this.waiterFirstName=waiterFirstName;
        this.waiterLastName=waiterLastName;
        this.waiterId=waiterId;

        this.restaurantMeanMark = restaurantMeanMark;
        this.restaurantFriendsMark = restaurantFriendsMark;
        this.restaurantMyMark = restaurantMyMark;

        DateFormat dfDate = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dfTime = new SimpleDateFormat("HH:mm");

        this.dateOfVisit = dfDate.format(reservation.getDateTimeStart());
        this.timeOfVisit = dfTime.format(reservation.getDateTimeStart());
    }
}



