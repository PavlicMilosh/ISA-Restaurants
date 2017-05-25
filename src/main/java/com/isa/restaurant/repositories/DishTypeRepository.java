package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.DishType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 5/24/2017.
 */
public interface DishTypeRepository extends JpaRepository<DishType, Long>
{
}
