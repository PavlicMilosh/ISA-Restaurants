package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Q on 13-May-17.
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>
{
    Reservation findById(Long id);

    @Query("SELECT r FROM Reservation r WHERE r.restaurant.id = :restaurant_id AND " +
            "((r.dateTimeEnd BETWEEN :date_start AND :date_end) OR (r.dateTimeStart BETWEEN :date_start AND :date_end))")
    List<Reservation> getReservationsByRestaurantAndDate(@Param("restaurant_id") Long restaurantId,
                                                         @Param("date_start") Date start,
                                                         @Param("date_end") Date end);

}
