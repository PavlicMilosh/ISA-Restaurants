package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Order;
import com.isa.restaurant.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 4/22/2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long>
{
    Order findById(Long id);

    //Order findByUser(User user);
}
