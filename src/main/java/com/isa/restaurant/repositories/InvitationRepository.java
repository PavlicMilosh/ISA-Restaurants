package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Q on 09-Jun-17.
 */
public interface InvitationRepository extends JpaRepository<Invitation, Long>
{
}
