package com.happyfit.happyfit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.happyfit.happyfit.models.Goal;
import com.happyfit.happyfit.models.Profile;
import com.happyfit.happyfit.models.dto.AddGoalDto;
import com.happyfit.happyfit.services.GoalService;
import com.happyfit.happyfit.services.ProfileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user/{userId}/profile/{profileId}/goal")
public class GoalController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private GoalService goalService;

    @GetMapping
    public ResponseEntity<Goal> getGoal(@PathVariable Integer userId, @PathVariable Integer profileId) {
        Profile profile = profileService.findById(profileId);
        if (profile == null || profile.getGoal() == null || !profile.getUser().getId().equals(userId))
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(profile.getGoal());
    }

    @PostMapping
    public ResponseEntity<Goal> createGoal(@PathVariable Integer userId, @PathVariable Integer profileId, @RequestBody @Valid AddGoalDto goalDto) {
        Profile profile = profileService.findById(profileId);
        if (profile == null || profile.getGoal() != null || !profile.getUser().getId().equals(userId))
            return ResponseEntity.badRequest().build();

        Goal goal = goalService.fromDto(goalDto);
        goal.setProfile(profile);
        Goal newGoal = goalService.create(goal);
        return ResponseEntity.ok(newGoal);
    }

    @PutMapping("/{goalId}")
    public ResponseEntity<Goal> updateGoal(@PathVariable Integer userId, @PathVariable Integer profileId, @RequestBody @Valid AddGoalDto goalDto) {
        Profile profile = profileService.findById(profileId);
        if (profile == null || profile.getGoal() == null || !profile.getUser().getId().equals(userId))
            return ResponseEntity.badRequest().build();

        Goal updatedGoal = goalService.fromDto(goalDto);
        updatedGoal.setId(profile.getGoal().getId());
        updatedGoal.setProfile(profile);
        Goal savedGoal = goalService.update(updatedGoal);
        return ResponseEntity.ok(savedGoal);
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Integer userId, @PathVariable Integer profileId) {
        Profile profile = profileService.findById(profileId);
        if (profile == null || profile.getGoal() == null || !profile.getUser().getId().equals(userId)) {
            return ResponseEntity.badRequest().build();
        }
        goalService.delete(profile.getGoal().getId());
        return ResponseEntity.noContent().build();
    }
}