package com.isa.restaurant.services;

import com.isa.restaurant.domain.DTO.WorkScheduleDTO;

import java.util.List;

/**
 * Created by Milos on 14-May-17.
 */
public interface WorkScheduleService
{
    boolean addWorkSchedule(Long userId, List<WorkScheduleDTO> workSchedule);
}
