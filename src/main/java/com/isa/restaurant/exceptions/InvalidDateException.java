package com.isa.restaurant.exceptions;

/**
 * Created by Q on 15-Jun-17.
 */
public class InvalidDateException extends Exception
{
    public InvalidDateException() {}

    public InvalidDateException(String message)
    {
        super(message);
    }
}
