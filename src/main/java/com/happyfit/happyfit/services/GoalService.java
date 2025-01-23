package com.happyfit.happyfit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyfit.happyfit.models.Goal;
import com.happyfit.happyfit.models.dto.AddGoalDto;
import com.happyfit.happyfit.repositories.GoalRepository;

import jakarta.validation.Valid;

@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public Goal findById(Integer id) {
        Optional<Goal> goal = goalRepository.findById(id);
        return goal.orElse(null);
    }

    @Transactional
    public Goal create(Goal goal) {
        return goalRepository.save(goal);
    }

    @Transactional
    public Goal update(Goal goal) {
        if (goal.getId() == null) throw new IllegalArgumentException("Goal ID não pode ser null");
        return goalRepository.save(goal);
    }

    @Transactional
    public void delete(Integer id) {
        goalRepository.deleteById(id);
    }

    public Goal fromDto(@Valid AddGoalDto goalDto) {
        Goal goal = new Goal();
        goal.setCaloricIntake(goalDto.getCaloricIntake());
        goal.setProteinIntake(goalDto.getProteinIntake());
        goal.setFatIntake(goalDto.getFatIntake());
        goal.setCarbIntake(goalDto.getCarbIntake());
        return goal;
    }
}
