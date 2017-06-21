package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.DTO.RegionDTO;
import com.isa.restaurant.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Q on 18-Jun-17.
 */
public interface RegionRepository extends JpaRepository<Region, Long>
{
}
