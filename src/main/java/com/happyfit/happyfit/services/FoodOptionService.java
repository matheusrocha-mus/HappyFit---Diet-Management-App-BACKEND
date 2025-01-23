package com.happyfit.happyfit.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.happyfit.happyfit.models.FoodOption;
import com.happyfit.happyfit.repositories.FoodOptionRepository;

@Service
public class FoodOptionService {
    
    @Autowired
    private FoodOptionRepository foodOptionRepository;

    public List<FoodOption> getAllFoodOptions() {
        return foodOptionRepository.findAll();
    }

    public FoodOption getFoodOptionById(Integer id) {
        return foodOptionRepository.findById(id).orElse(null);
    }

    public List<FoodOption> getFoodOptionsByName(String name) {
        String normalizedName = name.toLowerCase();
        return foodOptionRepository.findByName(normalizedName);
    }

    public FoodOption saveFoodOption(FoodOption foodOption) {
        validateFoodOption(foodOption);
        return foodOptionRepository.save(foodOption);
    }

    public FoodOption updateFoodOption(Integer id, FoodOption updatedFoodOption) {
        validateFoodOption(updatedFoodOption);

        FoodOption existingFoodOption = foodOptionRepository.findById(id).orElse(null);
        if (existingFoodOption != null) {
            existingFoodOption.setName(updatedFoodOption.getName());
            existingFoodOption.setCalories(updatedFoodOption.getCalories());
            existingFoodOption.setProteins(updatedFoodOption.getProteins());
            existingFoodOption.setCarbs(updatedFoodOption.getCarbs());
            existingFoodOption.setFats(updatedFoodOption.getFats());
            existingFoodOption.setPortion(updatedFoodOption.getPortion());
            
            return foodOptionRepository.save(existingFoodOption);
        } else {
            throw new IllegalArgumentException("Opção de comida não encontrada: " + id);
        }
    }

    public void deleteFoodOption(Integer id) {
        foodOptionRepository.deleteById(id);
    }

    private void validateFoodOption(FoodOption foodOption) {
        if (foodOption.getName() == null || foodOption.getName().isEmpty()) {
            throw new IllegalArgumentException("O nome da comida no pode ser nulo ou vazio");
        }
        if (foodOption.getCalories() == null) {
            throw new IllegalArgumentException("As calorias da comida devem ser maiores que zero");
        }
        if (foodOption.getProteins() == null) {
            throw new IllegalArgumentException("As protenas da comida devem ser maiores que zero");
        }
        if (foodOption.getCarbs() == null) {
            throw new IllegalArgumentException("Os carboidratos da comida devem ser maiores que zero");
        }
        if (foodOption.getFats() == null) {
            throw new IllegalArgumentException("As gorduras da comida devem ser maiores que zero");
        }
        if (foodOption.getPortion() == null) {
            throw new IllegalArgumentException("A poro da comida no pode ser nula");
        }
    }
}