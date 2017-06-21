package com.isa.restaurant.services.implementation;

import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.Invitation;
import com.isa.restaurant.domain.InvitationStatus;
import com.isa.restaurant.domain.VerificationToken;
import com.isa.restaurant.repositories.InvitationRepository;
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
    private final InvitationRepository invitationRepository;


    @Autowired
    public VerificationTokenServiceImpl(UserRepository userRepository,
                                        VerificationTokenRepository verificationTokenRepository,
                                        InvitationRepository invitationRepository)
    {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.invitationRepository = invitationRepository;
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


    @Override
    public Boolean acceptInvitation(Long guestId, Long invitationId, String verificationTokenValue)
    {
        Invitation invitation = invitationRepository.findByUserIdAndInvitationId(guestId, invitationId);
        VerificationToken token = verificationTokenRepository.findByToken(verificationTokenValue);

        if (invitation == null || token == null) return false;
        if (!token.isOwner(invitation.getInvited().getId())) return false;

        invitation.setStatus(InvitationStatus.ACCEPTED);
        verificationTokenRepository.delete(token.getId());

        return true;
    }


    public String getTokenByUserIdAndPurpose(Long userId, String purpose)
    {
        return verificationTokenRepository.findTokenByUserIdAndPurpose(userId, purpose);
    }



}
