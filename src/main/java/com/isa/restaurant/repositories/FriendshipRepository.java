package com.isa.restaurant.repositories;

import com.isa.restaurant.domain.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface FriendshipRepository extends JpaRepository<Friendship, Long>
{

    Friendship findById(Long id);


    @Query("SELECT f FROM Friendship f WHERE " +
            "LOWER(f.status) = 'accepted' AND (" +
            "f.firstUser.id = :user_id OR f.secondUser.id = :user_id)")
    Set<Friendship> findAllAcceptedFriendshipsByUserId (@Param("user_id") Long userId);


    @Query("SELECT f FROM Friendship f WHERE " +
            "LOWER(f.status) = 'pending' AND " +
            "f.actionUser.id != :user_id")
    Set<Friendship> findAllIncomingFriendRequestsByUserId (@Param("user_id") Long userId);


    @Query("SELECT f FROM Friendship f WHERE " +
            "(f.firstUser.id = :user1_id AND f.secondUser.id = :user2_id) OR " +
            "(f.firstUser.id = :user2_id AND f.secondUser.id = :user1_id)")
    Friendship findByBothUsers (@Param("user1_id") Long firstUserId, @Param("user2_id") Long secondUserId);
}
