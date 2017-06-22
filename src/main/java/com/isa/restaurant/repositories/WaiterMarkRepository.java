package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.WaiterMark;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by djuro on 6/22/2017.
 */
public interface WaiterMarkRepository extends JpaRepository<WaiterMark, Long>
{
}
