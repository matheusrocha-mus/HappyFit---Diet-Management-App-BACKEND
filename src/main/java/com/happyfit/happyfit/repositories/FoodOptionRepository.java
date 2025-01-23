package com.happyfit.happyfit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happyfit.happyfit.models.FoodOption;

@Repository
public interface FoodOptionRepository extends JpaRepository<FoodOption, Integer> {
    List<FoodOption> findByName(String name);
}
