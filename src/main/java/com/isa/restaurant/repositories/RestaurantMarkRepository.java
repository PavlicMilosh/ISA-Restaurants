package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.RestaurantMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Q on 06-Jun-17.
 */
public interface RestaurantMarkRepository extends JpaRepository<RestaurantMark, Long>
{
    @Query("SELECT AVG(m.value) FROM RestaurantMark m WHERE m.restaurant.id = :restaurant_id GROUP BY m.id")
    Double getRestaurantMeanMark(@Param("restaurant_id") Long restaurantId);

    @Query("SELECT m FROM RestaurantMark m WHERE m.restaurant.id = :restaurant_id AND m.guest.id = :guest_id")
    RestaurantMark getMarkByGuestIdAndRestaurantId(@Param("guest_id") Long guestId, @Param("restaurant_id") Long restaurantId);
}
