package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Milos on 13-May-17.
 */
public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long>
{
}
