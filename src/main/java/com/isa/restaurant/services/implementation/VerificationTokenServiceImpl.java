package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.VerificationToken;
import com.isa.restaurant.repositories.UserRepository;
import com.isa.restaurant.repositories.VerificationTokenRepository;
import com.isa.restaurant.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VerificationTokenServiceImpl implements VerificationTokenService
{
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;


    @Autowired
    public VerificationTokenServiceImpl(UserRepository userRepository, VerificationTokenRepository verificationTokenRepository)
    {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
    }


    @Override
    public Boolean activateGuest(Long guestId, String verificationTokenValue)
    {
        Guest guest = (Guest) userRepository.findById(guestId);
        VerificationToken token = verificationTokenRepository.findByToken(verificationTokenValue);

        if (guest == null || token == null) return false;
        if (!token.isOwner(guestId)) return false;

        guest.setEnabled(true);
        verificationTokenRepository.delete(token.getId());

        return true;
    }


    public String getTokenByUserIdAndPurpose(Long userId, String purpose)
    {
        String token = verificationTokenRepository.findTokenByUserIdAndPurpose(userId, purpose);
        return token;
    }
}
