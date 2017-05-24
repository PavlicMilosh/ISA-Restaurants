package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.RestaurantOrders;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 5/24/2017.
 */
public interface RestaurantOrdersRepository extends JpaRepository<RestaurantOrders, Long>
{
    RestaurantOrders findById(Long id);

    RestaurantOrders findByRestaurantId(Long id);
}
