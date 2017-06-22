package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Bartender;
import com.isa.restaurant.domain.Day;
import com.isa.restaurant.domain.WorkSchedule;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.text.ParseException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by Milos on 14-May-17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class WorkScheduleRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkScheduleRepository workScheduleRepository;

    @Before
    public void setUp()
    {
        Bartender b = new Bartender("asdf", "asdf", "asdf", "asdf");
        userRepository.save(b);
    }

    @Test
    public void testSave() throws ParseException
    {
        Bartender b = (Bartender) userRepository.findByEmail("asdf");
        WorkSchedule ws = new WorkSchedule("12:00", "22:00", Day.MONDAY);
        ws.setWorker(b);
        ws.setRestaurant(b.getRestaurant());
        WorkSchedule saved = workScheduleRepository.save(ws);
        Assert.assertEquals(ws, saved);
    }

    @After
    public void tearDown()
    {
        Bartender b = (Bartender) userRepository.findByEmail("asdf");
        userRepository.delete(b.getId());
    }
}
