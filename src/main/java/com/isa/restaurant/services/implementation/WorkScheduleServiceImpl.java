package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.*;
import com.isa.restaurant.domain.DTO.WorkScheduleDTO;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.repositories.WorkScheduleRepository;
import com.isa.restaurant.services.WorkScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;

/**
 * Created by Milos on 14-May-17.
 */
@Transactional
@Service
public class WorkScheduleServiceImpl implements WorkScheduleService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Override
    public boolean addWorkSchedule(Long userId, List<WorkScheduleDTO> workSchedule)
    {
        User u = userRepository.findOne(userId);
        Restaurant r = null;
        if(u instanceof Bartender)
        {
            r = ((Bartender) u).getRestaurant();
        }
        else if(u instanceof Cook)
        {
            r = ((Cook) u).getRestaurant();
        }
        else if(u instanceof Waiter)
        {
            r = ((Waiter) u).getRestaurant();
        }

        for(WorkScheduleDTO ws : workSchedule)
        {
            ws.setWorker(u);
            ws.setRestaurant(r);
            WorkSchedule toSave;
            try
            {
                toSave = new WorkSchedule(ws);
            }
            catch (ParseException e)
            {
                return false;
            }
            workScheduleRepository.save(toSave);
        }

        return true;
    }
}
