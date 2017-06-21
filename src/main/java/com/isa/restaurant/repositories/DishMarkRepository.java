package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.DishMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by djuro on 6/20/2017.
 */
public interface DishMarkRepository extends JpaRepository<DishMark, Long>
{
    @Query("SELECT dm FROM DishMark dm WHERE dm.dish.id = :dish_id")
    DishMark findByDishId(@Param("dish_id") Long dishId);
}
