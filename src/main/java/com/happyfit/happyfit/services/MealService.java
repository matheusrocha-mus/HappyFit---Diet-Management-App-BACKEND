package com.happyfit.happyfit.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyfit.happyfit.models.FoodOption;
import com.happyfit.happyfit.models.Diet;
import com.happyfit.happyfit.models.Diary;
import com.happyfit.happyfit.models.Food;
import com.happyfit.happyfit.models.Meal;
import com.happyfit.happyfit.models.dto.FoodDto;
import com.happyfit.happyfit.models.dto.MealDto;
import com.happyfit.happyfit.repositories.FoodOptionRepository;
import com.happyfit.happyfit.repositories.FoodRepository;
import com.happyfit.happyfit.repositories.MealRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class MealService {

    private static final Logger logger = LoggerFactory.getLogger(MealService.class);

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodOptionRepository foodOptionRepository;

    public Meal findById(Integer id) {
        Optional<Meal> meal = this.mealRepository.findById(id);
        return meal.orElse(null);
    }

    public Meal createWithoutFoods(Meal meal) {
        logger.info("Criando refeição sem alimentos: {}", meal);
        return mealRepository.save(meal);
    }

    public Meal create(Meal meal, List<FoodDto> foodDtos) {
        logger.info("Criando refeição: {}", meal);

        List<Food> foods = foodDtos.stream()
            .map(dto -> {
                Optional<FoodOption> foodOption = foodOptionRepository.findById(dto.getFoodOption());
                Food food = new Food();
                food.setMeal(meal);
                food.setFoodOption(foodOption.get());
                food.setQuantity(dto.getQuantity());
                //foodRepository.save(food);
                return food;
            })
            .collect(Collectors.toList())
        ;

        meal.setFoods(foods);
        Meal savedMeal = mealRepository.save(meal);

        return savedMeal;
    }

    public Meal update(MealDto existingMealDto, Meal updatedMeal, MealDto updatedMealDto) {
        logger.info("Editando refeição: {}", existingMealDto);

        List<Food> foods = updatedMealDto.getFoods().stream()
            .map(newFood -> {
                Optional<FoodOption> foodOption = foodOptionRepository.findById(newFood.getFoodOption());
                if (foodOption.isPresent()) {
                    Food savedFood = new Food();
                    savedFood.setMeal(updatedMeal);
                    savedFood.setFoodOption(foodOption.get());
                    savedFood.setQuantity(newFood.getQuantity());

                    Optional<FoodDto> matchingOldFood = existingMealDto.getFoods().stream()
                        .filter(oldFood -> oldFood.getFoodOption().equals(newFood.getFoodOption()))
                        .findFirst()
                    ;
                    if (matchingOldFood.isPresent()) savedFood.setId(matchingOldFood.get().getId()); // Update existing food if it matches a new food

                    return savedFood;
                } else return null;
            })
            .filter(Objects::nonNull)
            .collect(Collectors.toList())
        ;

        updatedMeal.setFoods(foods);
        Meal savedMeal = mealRepository.save(updatedMeal);

        List<Integer> foodsToDelete = existingMealDto.getFoods().stream()
            .filter(oldFood -> foods.stream()
                .noneMatch(newFood -> oldFood.getFoodOption().equals(newFood.getFoodOption().getId())))
            .map(FoodDto::getId)
            .collect(Collectors.toList())
        ;

        foodsToDelete.forEach(oldFoodId -> {
            logger.info("Deletando alimento: {}", oldFoodId);
            foodRepository.deleteById(oldFoodId);
            //foods.removeIf(food -> food.getId().equals(oldFoodId));
        });

        return savedMeal;
    }

    public void delete(Integer mealId) {
        logger.info("Deletando refeição com ID: {}", mealId);

        Optional<Meal> meal = mealRepository.findById(mealId);
        if (meal.isPresent()) {
            for (Food food : meal.get().getFoods()) {
                foodRepository.deleteById(food.getId());
            }

            mealRepository.deleteById(mealId);
        } else logger.warn("Refeição com o ID {} não encontrada", mealId);
    }

    public void addFoodToMeal(Meal meal, FoodOption foodOption, Double quantity) {
        Food food = new Food();

        food.setMeal(meal);
        food.setFoodOption(foodOption);
        food.setQuantity(quantity);
        foodRepository.save(food);
    }

    public List<Meal> getMealsByDiet(Diet diet) {
        return mealRepository.findByDiet(diet);
    }

    public List<Meal> getMealsByDiary(Diary diary) {
        return mealRepository.findByDiary(diary);
    }
}