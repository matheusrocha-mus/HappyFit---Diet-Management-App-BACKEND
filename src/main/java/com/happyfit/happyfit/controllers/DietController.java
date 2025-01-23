package com.happyfit.happyfit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.happyfit.happyfit.models.Diet;
import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.dto.DietDto;
import com.happyfit.happyfit.services.DietService;
import com.happyfit.happyfit.services.UserService;

@RestController
@RequestMapping("/user/{userId}/diet")
public class DietController {

    @Autowired
    private DietService dietService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Diet> findDiet(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        Diet diet = user.getDiet();

        return ResponseEntity.ok(diet);
    }

    @PostMapping
    public ResponseEntity<Diet> addDiet(@PathVariable Integer userId, @RequestBody DietDto dietDto) {
        User user = this.userService.findById(userId);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Diet diet = this.dietService.fromDto(dietDto);
        Diet newDiet = dietService.create(diet);

        user = this.userService.addDiet(user, newDiet);

        return ResponseEntity.ok(newDiet);
    }

    @PutMapping
    ResponseEntity<Diet> updateDiet(@RequestBody DietDto dietDto, @PathVariable Integer userId) {
        User user = this.userService.findById(userId);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Diet diet = this.dietService.fromDto(dietDto);
        diet.setId(user.getDiet().getId());
        Diet updatedDiet = dietService.update(user, diet);

        return ResponseEntity.ok(updatedDiet);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDiet(@PathVariable Integer userId) {
        User user = this.userService.findById(userId);
        Diet diet = user.getDiet();
        this.dietService.delete(diet);
        return ResponseEntity.noContent().build();
    }
}