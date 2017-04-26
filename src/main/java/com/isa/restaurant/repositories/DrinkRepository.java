package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Created by Milos on 23-Apr-17.
 */

public interface DrinkRepository extends JpaRepository<Drink, Long>
{
    Drink findById(Long id);
}
