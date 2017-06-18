package com.isa.restaurant.exceptions;

/**
 * Created by Q on 15-Jun-17.
 */
public class UserNotFoundException extends Exception
{
    public UserNotFoundException() {}

    public UserNotFoundException(String message)
    {
        super(message);
    }
}
