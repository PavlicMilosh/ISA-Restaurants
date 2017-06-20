package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Milos on 13-Apr-17.
 */
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>
{
    Restaurant findByName(String name);

    Restaurant findById(Long id);
}
