package com.isa.restaurant.exceptions;

/**
 * Created by Q on 15-Jun-17.
 */
public class ReservationException extends Exception
{
    public ReservationException() {}

    public ReservationException(String message)
    {
        super(message);
    }
}
