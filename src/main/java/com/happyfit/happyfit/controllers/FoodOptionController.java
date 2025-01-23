package com.happyfit.happyfit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.happyfit.happyfit.models.FoodOption;
import com.happyfit.happyfit.services.FoodOptionService;

@RestController
@RequestMapping("/foodOption")
public class FoodOptionController {

    @Autowired
    private FoodOptionService foodOptionService;

    @GetMapping
    public ResponseEntity<List<FoodOption>> getAllFoodOptions() {
        List<FoodOption> foodOptions = foodOptionService.getAllFoodOptions();
        return ResponseEntity.ok(foodOptions);
    }

    @GetMapping("/{foodOptionId}")
    public ResponseEntity<FoodOption> getFoodOptionById(@PathVariable Integer foodOptionId) {
        FoodOption foodOption = foodOptionService.getFoodOptionById(foodOptionId);
        if (foodOption == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foodOption);
    }

    // @GetMapping("/search")
    // public ResponseEntity<List<FoodOption>> getFoodOptionsByName(@RequestParam String name) {
    //     List<FoodOption> foodOptions = foodOptionService.getFoodOptionsByName(name);
    //     return ResponseEntity.ok(foodOptions);
    // }

    @PostMapping
    public ResponseEntity<FoodOption> createFoodOption(@RequestBody FoodOption foodOption) {
        FoodOption newFoodOption = foodOptionService.saveFoodOption(foodOption);
        return ResponseEntity.ok(newFoodOption);
    }

    @PutMapping("/{foodOptionId}")
    public ResponseEntity<FoodOption> updateFoodOption(@PathVariable Integer foodOptionId, @RequestBody FoodOption updatedFoodOption) {
        try {
            FoodOption updatedOption = foodOptionService.updateFoodOption(foodOptionId, updatedFoodOption);
            return ResponseEntity.ok(updatedOption);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @DeleteMapping("/{foodOptionId}")
    public ResponseEntity<Void> deleteFoodOption(@PathVariable Integer foodOptionId) {
        foodOptionService.deleteFoodOption(foodOptionId);
        return ResponseEntity.noContent().build();
    }
}
