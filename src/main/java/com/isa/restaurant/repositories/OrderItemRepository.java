package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 5/24/2017.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>
{
    OrderItem findById(Long id);
}
