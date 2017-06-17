package com.isa.restaurant.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

/**
 * Created by Q on 17-Jun-17.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(suppressConstructorProperties = true)
public class ReservationUpdateDTO
{
    private Long reservationId;
    private Long guestId;
    private Long orderId;
    private Set<DrinkOrderDTO> drinkOrders;
    private Set<DishOrderDTO> dishOrders;
}
