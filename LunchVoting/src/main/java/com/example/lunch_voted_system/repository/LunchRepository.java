package com.example.lunch_voted_system.repository;

import com.example.lunch_voted_system.model.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional(readOnly = true)
public interface LunchRepository extends JpaRepository<Lunch, Integer> {

    @Query("SELECT l FROM Lunch l WHERE l.restaurantName = LOWER(:restaurantName)")
    Optional<Lunch> findByRestaurantNameContainingIgnoreCase(String restaurantName);

    @Query("SELECT l FROM Lunch l")
    List<Lunch> getAllLunches();

    @Modifying
    @Query("UPDATE Lunch l SET l.votes = :newValue")
    void clearVotes(@Param("newValue") String newValue);

}