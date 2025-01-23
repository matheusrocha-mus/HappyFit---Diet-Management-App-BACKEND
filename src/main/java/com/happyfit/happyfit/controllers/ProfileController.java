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

import com.happyfit.happyfit.models.Profile;
import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.dto.ProfileDto;
import com.happyfit.happyfit.services.ProfileService;
import com.happyfit.happyfit.services.UserService;

@RestController
@RequestMapping("/user/{userId}/profile")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Profile> findProfile(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        Profile profile = user.getProfile();

        return ResponseEntity.ok(profile);
    }

    @PostMapping
    public ResponseEntity<Profile> addProfile(@PathVariable Integer userId, @RequestBody ProfileDto profileDto) {
        User user = this.userService.findById(userId);

        if (user == null) return ResponseEntity.badRequest().body(null);

        Profile profile = this.profileService.fromDto(profileDto);
        Profile newProfile = profileService.create(profile);

        user = this.userService.addProfile(user, newProfile);

        return ResponseEntity.ok(newProfile);
    }

    @PutMapping("/{profileId}")
    ResponseEntity<Profile> updateProfile(@PathVariable Integer userId, @PathVariable Integer profileId, @RequestBody ProfileDto profileDto) {
        User user = this.userService.findById(userId);

        if (user == null || !user.getProfile().getId().equals(profileId))
            return ResponseEntity.badRequest().body(null);

        Integer goalId = profileService.findById(profileId).getGoal().getId();

        Profile profile = this.profileService.fromDto(profileDto);
        profile.setId(profileId);
        Profile updatedProfile = profileService.update(profile, goalId);

        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Profile> deleteProfile(@PathVariable Integer userId, @PathVariable Integer profileId) {
        User user = this.userService.findById(userId);

        if (user == null || !user.getProfile().getId().equals(profileId))
            return ResponseEntity.badRequest().body(null);

        Profile profile = user.getProfile();
        this.profileService.delete(profile);
        return ResponseEntity.ok().body(profile);
    }
}