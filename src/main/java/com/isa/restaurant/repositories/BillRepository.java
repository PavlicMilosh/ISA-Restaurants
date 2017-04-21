package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Bill;
import com.isa.restaurant.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 4/21/2017.
 */
public interface BillRepository extends JpaRepository<Bill, Long>
{
    Bill findById(Long id);

    Bill findByUser(User user);
}
