package com.isa.restaurant.domain.DTO;

import com.isa.restaurant.domain.Day;
import com.isa.restaurant.domain.Restaurant;
import com.isa.restaurant.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Milos on 14-May-17.
 */
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
public class WorkScheduleDTO
{
    private Long id;

    private String startTime;

    private String endTime;

    private Day day;

    private User worker;

    private Restaurant restaurant;

    public WorkScheduleDTO(String startTime, String endTime, Day day)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
    }
}
