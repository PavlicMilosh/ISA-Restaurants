package com.isa.restaurant.services;

import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.VerificationToken;

/**
 * Created by Q on 21-Apr-17.
 */
public interface MailService
{
    void sendUserActivationEmail(Guest guest, String token);
}
