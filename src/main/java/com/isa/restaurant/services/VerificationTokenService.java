package com.isa.restaurant.services;

/**
 * Created by Q on 21-Apr-17.
 */
public interface VerificationTokenService
{
    Boolean activateGuest(Long guestId, String verificationTokenValue);
    String getTokenByUserId(Long userId);
}
