package com.happyfit.happyfit.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyfit.happyfit.models.Food;
// import com.happyfit.happyfit.repositories.FoodOptionRepository;
import com.happyfit.happyfit.repositories.FoodRepository;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    // @Autowired
    // private FoodOptionRepository foodOptionRepository;

    public Food create(Food food) {
        validateFood(food);
        return foodRepository.save(food);
    }

    public Food update(Food food) {
        validateFood(food);
        return foodRepository.save(food);
    }

    public void delete(Integer foodId) {
        foodRepository.deleteById(foodId);
    }

    public List<Food> findFoodsByMealId(Integer mealId) {
        return foodRepository.findByMealId(mealId);
    }

    public Food findById(Integer foodId) {
        Optional<Food> food = foodRepository.findById(foodId);
        return food.orElse(null);
    }

    private void validateFood(Food food) {
        if (food.getMeal() == null) {
            throw new IllegalArgumentException("A refeição não pode ser nula");
        }
        if (food.getFoodOption() == null) {
            throw new IllegalArgumentException("A opção de comida não pode ser nula");
        }
        if (food.getQuantity() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero");
        }
    }
}
