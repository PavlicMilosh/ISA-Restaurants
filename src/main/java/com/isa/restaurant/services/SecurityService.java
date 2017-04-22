package com.isa.restaurant.services;

/**
 * Created by Q on 21-Apr-17.
 */
public interface SecurityService
{
    String findLoggedInUsername();
    void autologin(String username, String password);
}
