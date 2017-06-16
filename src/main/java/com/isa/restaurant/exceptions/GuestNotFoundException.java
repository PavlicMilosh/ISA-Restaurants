package com.isa.restaurant.exceptions;

/**
 * Created by Q on 15-Jun-17.
 */
public class GuestNotFoundException extends Exception
{
    public GuestNotFoundException() {}

    public GuestNotFoundException(String message)
    {
        super(message);
    }
}
