package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Q on 21-Jun-17.
 */
public interface AddressRepository extends JpaRepository<Address, Long>
{
}
