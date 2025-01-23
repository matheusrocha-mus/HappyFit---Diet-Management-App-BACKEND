package com.happyfit.happyfit.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.happyfit.happyfit.models.FoodOption;
import com.happyfit.happyfit.models.Food;
import com.happyfit.happyfit.models.Meal;
import com.happyfit.happyfit.models.dto.FoodDto;
import com.happyfit.happyfit.services.FoodService;
import com.happyfit.happyfit.services.MealService;
import com.happyfit.happyfit.services.FoodOptionService;

@RestController
@RequestMapping("/user/{userId}")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private MealService mealService;

    @Autowired
    private FoodOptionService foodOptionService;

//INICIO REQUESTS DIETA
    @GetMapping("/diet/meal/{mealId}/food")
    public ResponseEntity<Map<String, Object>> getDietFoods(@PathVariable Integer userId, @PathVariable Integer mealId) {
        Meal meal = mealService.findById(mealId);
        if (meal == null || !meal.getDiet().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        List<Food> foods = foodService.findFoodsByMealId(mealId);

        Map<String, Object> response = new HashMap<>();
        response.put("meal", meal);
        response.put("foods", foods);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/diet/meal/{mealId}/food/{foodId}")
    public ResponseEntity<Food> getDietFoodById(@PathVariable Integer userId, @PathVariable Integer mealId, @PathVariable Integer foodId) {
        Food food = foodService.findById(foodId);
        if (food == null || !food.getMeal().getId().equals(mealId) || !food.getMeal().getDiet().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(food);
    }

    @PostMapping("/diet/meal/{mealId}/food")
    public ResponseEntity<Food> addDietFood(@PathVariable Integer userId, @PathVariable Integer mealId, @RequestBody FoodDto foodDto) {
        Meal meal = mealService.findById(mealId);
        if (meal == null || !meal.getDiet().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }

        FoodOption foodOption = foodOptionService.getFoodOptionById(foodDto.getFoodOption());
        if (foodOption == null) {
            return ResponseEntity.badRequest().build();
        }

        Food food = new Food();
        food.setFoodOption(foodOption);
        food.setMeal(meal);
        food.setQuantity(foodDto.getQuantity());

        Food newFood = foodService.create(food);

        return ResponseEntity.ok(newFood);
    }

    @PutMapping("/diet/meal/{mealId}/food/{foodId}")
    public ResponseEntity<Food> updateDietFood(@PathVariable Integer userId, @PathVariable Integer mealId, @PathVariable Integer foodId, @RequestBody Food updatedFood) {
        Food existingFood = foodService.findById(foodId);
        if (existingFood == null || !existingFood.getMeal().getId().equals(mealId) || !existingFood.getMeal().getDiet().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        updatedFood.setId(foodId);
        updatedFood.setMeal(existingFood.getMeal());
        Food savedFood = foodService.update(updatedFood);
        return ResponseEntity.ok(savedFood);
    }

    @DeleteMapping("/diet/meal/{mealId}/food/{foodId}")
    public ResponseEntity<Void> deleteDietFood(@PathVariable Integer userId, @PathVariable Integer mealId, @PathVariable Integer foodId) {
        Food food = foodService.findById(foodId);
        if (food == null || !food.getMeal().getId().equals(mealId) || !food.getMeal().getDiet().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        foodService.delete(foodId);
        return ResponseEntity.noContent().build();
    }
//FIM REQUESTS DIETA



//INICIO REQUESTS DIARIO
    @GetMapping("/diary/{diaryId}/meal/{mealId}/food")
    public ResponseEntity<Map<String, Object>> getDiaryFoods(@PathVariable Integer userId, @PathVariable Integer diaryId, @PathVariable Integer mealId) {
        Meal meal = mealService.findById(mealId);
        if (meal == null || !meal.getDiary().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        List<Food> foods = foodService.findFoodsByMealId(mealId);

        Map<String, Object> response = new HashMap<>();
        response.put("meal", meal);
        response.put("foods", foods);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/diary/{diaryId}/meal/{mealId}/food/{foodId}")
    public ResponseEntity<Food> getDiaryFoodById(@PathVariable Integer userId, @PathVariable Integer diaryId, @PathVariable Integer mealId, @PathVariable Integer foodId) {
        Food food = foodService.findById(foodId);
        if (food == null || !food.getMeal().getId().equals(mealId) || !food.getMeal().getDiary().getId().equals(diaryId) || !food.getMeal().getDiary().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(food);
    }

    @PostMapping("/diary/{diaryId}/meal/{mealId}/food")
    public ResponseEntity<Food> addDiaryFood(@PathVariable Integer userId, @PathVariable Integer diaryId, @PathVariable Integer mealId, @RequestBody FoodDto foodDto) {
        Meal meal = mealService.findById(mealId);
        if (meal == null || !meal.getDiary().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }

        FoodOption foodOption = foodOptionService.getFoodOptionById(foodDto.getFoodOption());
        if (foodOption == null) {
            return ResponseEntity.badRequest().build();
        }

        Food food = new Food();
        food.setFoodOption(foodOption);
        food.setMeal(meal);
        food.setQuantity(foodDto.getQuantity());

        Food newFood = foodService.create(food);

        return ResponseEntity.ok(newFood);
    }

    @PutMapping("/diary/{diaryId}/meal/{mealId}/food/{foodId}")
    public ResponseEntity<Food> updateDiaryFood(@PathVariable Integer userId, @PathVariable Integer diaryId, @PathVariable Integer mealId, @PathVariable Integer foodId, @RequestBody Food updatedFood) {
        Food existingFood = foodService.findById(foodId);
        if (existingFood == null || !existingFood.getMeal().getId().equals(mealId) || !existingFood.getMeal().getDiary().getId().equals(diaryId) || !existingFood.getMeal().getDiary().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        updatedFood.setId(foodId);
        updatedFood.setMeal(existingFood.getMeal());
        Food savedFood = foodService.update(updatedFood);
        return ResponseEntity.ok(savedFood);
    }

    @DeleteMapping("/diary/{diaryId}/meal/{mealId}/food/{foodId}")
    public ResponseEntity<Void> deleteDiaryFood(@PathVariable Integer userId, @PathVariable Integer diaryId, @PathVariable Integer mealId, @PathVariable Integer foodId) {
        Food food = foodService.findById(foodId);
        if (food == null || !food.getMeal().getId().equals(mealId) || !food.getMeal().getDiary().getId().equals(diaryId) || !food.getMeal().getDiary().getUser().getId().equals(userId)) {
            return ResponseEntity.notFound().build();
        }
        foodService.delete(foodId);
        return ResponseEntity.noContent().build();
    }
//FIM REQUESTS DIARIO
}