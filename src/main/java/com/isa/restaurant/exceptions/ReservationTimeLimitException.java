package com.isa.restaurant.exceptions;

/**
 * Created by Q on 15-Jun-17.
 */
public class ReservationTimeLimitException extends Exception
{
    public ReservationTimeLimitException() {}

    public ReservationTimeLimitException(String message)
    {
        super(message);
    }
}
