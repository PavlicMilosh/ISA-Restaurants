package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Milos on 04-Jun-17.
 */
public interface ShoppingRepository extends JpaRepository<ShoppingList, Long>
{
}
