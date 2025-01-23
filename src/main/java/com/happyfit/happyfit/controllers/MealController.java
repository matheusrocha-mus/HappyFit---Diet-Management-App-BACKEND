package com.happyfit.happyfit.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.happyfit.happyfit.models.Meal;
import com.happyfit.happyfit.models.dto.FoodDto;
import com.happyfit.happyfit.models.dto.MealDto;
import com.happyfit.happyfit.models.Diet;
import com.happyfit.happyfit.models.Food;
import com.happyfit.happyfit.models.Diary;
import com.happyfit.happyfit.services.MealService;
import com.happyfit.happyfit.services.DietService;
import com.happyfit.happyfit.services.DiaryService;

@RestController
@RequestMapping("/user/{userId}")
public class MealController {

    @Autowired
    private MealService mealService;

    @Autowired
    private DietService dietService;

    @Autowired
    private DiaryService diaryService;

//INICIO REQUESTS DIETA
    @GetMapping("/diet/meal")
    public ResponseEntity<Map<String, Object>> getDietMeals(@PathVariable Integer userId) {
        Diet diet = dietService.findByUserId(userId);
        if (diet == null) {
            return ResponseEntity.notFound().build();
        }
        List<Meal> meals = mealService.getMealsByDiet(diet);

        Map<String, Object> response = new HashMap<>();
        response.put("diet", diet);
        response.put("meals", meals);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/diet/meal/{mealId}")
    public ResponseEntity<Meal> getDietMealById(@PathVariable Integer userId, @PathVariable Integer mealId) {
        Meal meal = mealService.findById(mealId);
        if (meal == null || !meal.getDiet().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(meal);
    }

    @PostMapping("/diet/meal")
    public ResponseEntity<Meal> createDietMeal(@PathVariable Integer userId, @RequestBody MealDto mealDto) {

        Diet diet = dietService.findByUserId(userId);
        if (diet == null) {
            return ResponseEntity.notFound().build();
        }

        Meal meal = new Meal();
        meal.setName(mealDto.getName());
        meal.setDiet(diet);

        Meal newMeal = mealService.create(meal, mealDto.getFoods());
        return ResponseEntity.ok(newMeal);
    }

    @PutMapping("/diet/meal/{mealId}")
    public ResponseEntity<Meal> updateDietMeal(@PathVariable Integer userId, @PathVariable Integer mealId, @RequestBody MealDto updatedMealDto) {
        Meal existingMeal = mealService.findById(mealId);
        if (existingMeal == null || !existingMeal.getDiet().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }

        List<FoodDto> foodDtos = new ArrayList<FoodDto>();
        for (Food food : existingMeal.getFoods()) {
            FoodDto foodDto = new FoodDto(food.getId(), food.getFoodOption().getId(), food.getQuantity());
            foodDtos.add(foodDto);
        }
        MealDto existingMealDto = new MealDto(existingMeal.getName(), foodDtos);

        Meal updatedMeal = new Meal();
        updatedMeal.setId(mealId);
        updatedMeal.setDiet(existingMeal.getDiet());
        updatedMeal.setName(updatedMealDto.getName());

        Meal savedMeal = mealService.update(existingMealDto, updatedMeal, updatedMealDto);

        return ResponseEntity.ok(savedMeal);
    }

    @DeleteMapping("/diet/meal/{mealId}")
    public ResponseEntity<Void> deleteDietMeal(@PathVariable Integer userId, @PathVariable Integer mealId) {
        Meal meal = mealService.findById(mealId);
        if (meal == null || !meal.getDiet().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        mealService.delete(mealId);
        return ResponseEntity.noContent().build();
    }
//FIM REQUESTS DIETA



//INICIO REQUESTS DIARIO
    @GetMapping("/diary/{diaryId}/meal")
    public ResponseEntity<Map<String, Object>> getDiaryMeals(@PathVariable Integer userId, @PathVariable Integer diaryId) {
        Diary diary = diaryService.findById(diaryId);
        if (diary == null) {
            return ResponseEntity.notFound().build();
        }
        List<Meal> meals = mealService.getMealsByDiary(diary);

        Map<String, Object> response = new HashMap<>();
        response.put("diary", diary);
        response.put("meals", meals);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/diary/{diaryId}/meal/{mealId}")
    public ResponseEntity<Meal> getDiaryMealById(@PathVariable Integer userId, @PathVariable Integer diaryId, @PathVariable Integer mealId) {
        Meal meal = mealService.findById(mealId);
        if (meal == null || !meal.getDiary().getId().equals(diaryId) || !meal.getDiary().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(meal);
    }

    @PostMapping("/diary/{diaryId}/meal")
    public ResponseEntity<Meal> createDiaryMeal(@PathVariable Integer userId, @PathVariable Integer diaryId, @RequestBody MealDto mealDto) {

        Diary diary = diaryService.findById(diaryId);
        if (diary == null) {
            return ResponseEntity.notFound().build();
        }

        Meal meal = new Meal();
        meal.setName(mealDto.getName());
        meal.setDiary(diary);

        if (mealDto.getFoods() == null || mealDto.getFoods().isEmpty()) {
            return ResponseEntity.ok(mealService.createWithoutFoods(meal));
        } else {
            return ResponseEntity.ok(mealService.create(meal, mealDto.getFoods()));
        }
    }

    @PutMapping("/diary/{diaryId}/meal/{mealId}")
    public ResponseEntity<Meal> updateDiaryMeal(@PathVariable Integer userId, @PathVariable Integer diaryId, @PathVariable Integer mealId, @RequestBody MealDto updatedMealDto) {
        Meal existingMeal = mealService.findById(mealId);
        if (existingMeal == null || !existingMeal.getDiary().getId().equals(diaryId) || !existingMeal.getDiary().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }

        List<FoodDto> foodDtos = new ArrayList<FoodDto>();
        for (Food food : existingMeal.getFoods()) {
            FoodDto foodDto = new FoodDto(food.getId(), food.getFoodOption().getId(), food.getQuantity());
            foodDtos.add(foodDto);
        }
        MealDto existingMealDto = new MealDto(existingMeal.getName(), foodDtos);

        Meal updatedMeal = new Meal();
        updatedMeal.setId(mealId);
        updatedMeal.setDiary(existingMeal.getDiary());
        updatedMeal.setName(updatedMealDto.getName());

        Meal savedMeal = mealService.update(existingMealDto, updatedMeal, updatedMealDto);

        return ResponseEntity.ok(savedMeal);
    }

    @DeleteMapping("/diary/{diaryId}/meal/{mealId}")
    public ResponseEntity<Void> deleteDiaryMeal(@PathVariable Integer userId, @PathVariable Integer diaryId, @PathVariable Integer mealId) {
        Meal meal = mealService.findById(mealId);
        if (meal == null || !meal.getDiary().getId().equals(diaryId) || !meal.getDiary().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        mealService.delete(mealId);
        return ResponseEntity.noContent().build();
    }
//FIM REQUESTS DIARIO
}