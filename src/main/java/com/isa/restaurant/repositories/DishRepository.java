package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 4/24/2017.
 */
public interface DishRepository extends JpaRepository<Dish, Long>
{
    Dish findById(Long id);
}
