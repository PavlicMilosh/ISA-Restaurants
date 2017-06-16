package com.isa.restaurant.ulitity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Q on 16-May-17.
 */
public class Utilities
{
    public static Date createDateFromString(String date, String time)
    {
        Date reservationDate = null;
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try
        {
            reservationDate = df.parse(date + " " + time);
        }
        catch (ParseException e)
        {
            return null;
        }
        return reservationDate;
    }


    public static Date addMinutesToDate(Date date, Long minutes)
    {
        if (date == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes.intValue());
        return cal.getTime();
    }


    public static Long diffDatesToMinutes(Date date1, Date date2)
    {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        Long millis = Math.abs(c1.getTimeInMillis() - c2.getTimeInMillis());
        Long minutes = (millis / 60000);
        return minutes;
    }
}
