package com.happyfit.happyfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happyfit.happyfit.models.Goal;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Integer> {

}