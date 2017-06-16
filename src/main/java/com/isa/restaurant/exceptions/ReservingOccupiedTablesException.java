package com.isa.restaurant.exceptions;

/**
 * Created by Q on 15-Jun-17.
 */
public class ReservingOccupiedTablesException extends Exception
{
    public ReservingOccupiedTablesException() {}

    public ReservingOccupiedTablesException(String message)
    {
        super(message);
    }
}
