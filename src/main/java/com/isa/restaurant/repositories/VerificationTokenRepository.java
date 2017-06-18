package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Q on 21-Apr-17.
 */
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long>
{
    VerificationToken findById(Long verificationTokenId);

    VerificationToken findByToken(String verificationTokenValue);

    @Query("SELECT vt.token FROM VerificationToken vt WHERE vt.guest.id = :user_id")
    String findTokenByUserId(@Param("user_id") Long userId);

    @Query("SELECT vt.token FROM VerificationToken vt WHERE vt.guest.id = :user_id AND vt.purpose = :purpose")
    String findTokenByUserIdAndPurpose(@Param("user_id") Long userId, @Param("purpose") String purpose);
}
