package com.happyfit.happyfit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happyfit.happyfit.models.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Integer> {

    List<Food> findByMealId(Integer mealId);

}
