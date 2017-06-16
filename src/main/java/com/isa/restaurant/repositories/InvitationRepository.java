package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Q on 09-Jun-17.
 */
public interface InvitationRepository extends JpaRepository<Invitation, Long>
{
    @Query("SELECT i FROM Invitation i WHERE i.invited.id = :user_id")
    Invitation findByUserId(@Param("user_id") Long userId);
}
