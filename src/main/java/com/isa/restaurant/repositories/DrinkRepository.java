package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

/**
<<<<<<< HEAD
 * Created by Milos on 23-Apr-17.
 */
public interface DrinkRepository extends JpaRepository<Drink, Long>
{
=======
 * Created by djuro on 4/24/2017.
 */
public interface DrinkRepository extends JpaRepository<Drink, Long>
{
    Drink findById(Long id);
>>>>>>> 8efdc50259b27e1ea03a0f56b408089ef60393a9
}
