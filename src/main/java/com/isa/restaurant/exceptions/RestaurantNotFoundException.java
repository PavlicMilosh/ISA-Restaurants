package com.isa.restaurant.exceptions;

/**
 * Created by Q on 15-Jun-17.
 */
public class RestaurantNotFoundException extends Exception
{
    public RestaurantNotFoundException() {}

    public RestaurantNotFoundException(String message)
    {
        super(message);
    }
}
