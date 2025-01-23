package com.happyfit.happyfit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.Diet;
import com.happyfit.happyfit.models.dto.DietDto;
import com.happyfit.happyfit.repositories.DietRepository;
import com.happyfit.happyfit.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class DietService {

    @Autowired
    private DietRepository dietRepository;

    @Autowired
    private UserRepository userRepository;

    public Diet findById(Integer id) {
        Optional<Diet> diet = this.dietRepository.findById(id);
        return diet.orElse(null);
    }

    public Diet findByUserId(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get().getDiet();
        }
        return null;
    }

    @Transactional
    public Diet create(Diet diet) {
        diet.setId(null);
        diet = this.dietRepository.save(diet);
        return diet;
    }

    public Diet delete(Diet diet) {
        User user = diet.getUser();
        user.setDiet(null);
        this.dietRepository.delete(diet);
        return diet;
    }

    public Diet fromDto(@Valid DietDto dietDto) {
        Diet diet = new Diet();

        diet.setTotalCalories(dietDto.getTotalCalories());
        diet.setTotalProteins(dietDto.getTotalProteins());
        diet.setTotalCarbs(dietDto.getTotalCarbs());
        diet.setTotalFats(dietDto.getTotalFats());

        return diet;
    }

    public Diet update(User user, Diet diet) {
        this.dietRepository.save(diet);
        return diet;
    }
}
