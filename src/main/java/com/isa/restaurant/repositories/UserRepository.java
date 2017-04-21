package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Milos on 10-Apr-17.
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    User findByEmail(String email);
    User findById(Long id);
}
