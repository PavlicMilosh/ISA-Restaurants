package com.isa.restaurant.domain;

import com.isa.restaurant.domain.DTO.WorkScheduleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Milos on 12-May-17.
 */
@Entity
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Getter
@Setter
public class WorkSchedule
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "day")
    private Day day;

    @ManyToOne
    private User worker;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Region region;

    public WorkSchedule(String start, String end, Day day) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.startTime = sdf.parse(start);
        this.endTime = sdf.parse(end);
        this.day = day;
    }

    public WorkSchedule(WorkScheduleDTO w) throws ParseException
    {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        this.startTime = sdf.parse(w.getStartTime());
        this.endTime = sdf.parse(w.getEndTime());
        this.day = w.getDay();
        this.worker = w.getWorker();
        this.restaurant = w.getRestaurant();
    }
}
