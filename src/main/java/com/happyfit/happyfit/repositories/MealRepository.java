package com.happyfit.happyfit.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happyfit.happyfit.models.Diet;
import com.happyfit.happyfit.models.Diary;
import com.happyfit.happyfit.models.Meal;

@Repository
public interface MealRepository extends JpaRepository<Meal, Integer> {

    List<Meal> findByDiet(Diet diet);

    List<Meal> findByDiary(Diary diary);

}
