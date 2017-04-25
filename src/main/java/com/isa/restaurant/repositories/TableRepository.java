package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Milos on 23-Apr-17.
 */
public interface TableRepository extends JpaRepository<RestaurantTable, Long>
{
}
