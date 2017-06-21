package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by djuro on 4/22/2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long>
{
    Order findById(Long id);

    @Query("SELECT o FROM Order o WHERE o.orderTable.id = :table_id")
    Set<Order> getTableOrders(@Param("table_id") Long tableId);
}
