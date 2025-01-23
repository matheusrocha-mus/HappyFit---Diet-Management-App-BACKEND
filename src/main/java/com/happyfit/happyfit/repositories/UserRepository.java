package com.happyfit.happyfit.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.happyfit.happyfit.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
    long count();

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = 1")
    long countUser();

    @Query("SELECT COUNT(u) FROM User u WHERE u.nutritionist IS NULL")
    long countUSerWidthNutritionist();

    
}
