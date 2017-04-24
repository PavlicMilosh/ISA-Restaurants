package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 4/24/2017.
 */
public interface DrinkRepository extends JpaRepository<Drink, Long>
{
    Drink findById(Long id);
}
