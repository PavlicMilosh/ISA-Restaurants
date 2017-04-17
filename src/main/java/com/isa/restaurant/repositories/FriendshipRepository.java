package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

/**
 * Created by Q on 15-Apr-17.
 */
public interface FriendshipRepository extends JpaRepository<Friendship, Long>
{

    Friendship findById(Long id);

    // neki query koji dobavlja sve friendship-e gde je first_user = guestId or second_user = guestId
    // pogledati kako se vraca set...
}
