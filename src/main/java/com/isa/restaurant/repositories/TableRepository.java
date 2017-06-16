package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by Milos on 23-Apr-17.
 */
public interface TableRepository extends JpaRepository<RestaurantTable, Long>
{
    @Lock(LockModeType.OPTIMISTIC)
    RestaurantTable findOne(Long id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("SELECT rt FROM RestaurantTable rt WHERE rt.restaurant.id = :restaurant_id")
    List<RestaurantTable> findByRestaurantId(@Param("restaurant_id") Long restaurantId);
}
