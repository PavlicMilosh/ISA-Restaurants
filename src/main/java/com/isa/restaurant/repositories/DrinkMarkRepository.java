package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.DishMark;
import com.isa.restaurant.domain.DrinkMark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by djuro on 6/20/2017.
 */
public interface DrinkMarkRepository extends JpaRepository<DrinkMark, Long>
{
    @Query("SELECT dm FROM DrinkMark dm WHERE dm.dish.id = :drink_id")
    DrinkMark findByDrinkId(@Param("drink_id") Long drinkId);
}
