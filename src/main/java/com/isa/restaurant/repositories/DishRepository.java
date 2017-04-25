package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Milos on 23-Apr-17.
 */
public interface DishRepository extends JpaRepository<Dish, Long>
{
}
