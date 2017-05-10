package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Guest;
import com.isa.restaurant.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

/**
 * Created by Milos on 10-Apr-17.
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);
    User findById(Long id);

    @Query("SELECT g FROM Guest g")
    Set<Guest> getAllGuests();
}
