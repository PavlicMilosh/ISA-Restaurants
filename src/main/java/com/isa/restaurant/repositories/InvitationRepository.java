package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Q on 09-Jun-17.
 */
public interface InvitationRepository extends JpaRepository<Invitation, Long>
{
    @Query("SELECT i FROM Invitation i WHERE i.invited.id = :user_id AND i.id = :id")
    Invitation findByUserIdAndInvitationId(@Param("user_id") Long userId, @Param("id") Long invitationId);

    @Query("SELECT i FROM Invitation i WHERE i.invited.id = :user_id AND LOWER(i.status) = 'accepted'")
    List<Invitation> getUsersAcceptedInvitationsByUserId(@Param("user_id") Long guestId);
}
