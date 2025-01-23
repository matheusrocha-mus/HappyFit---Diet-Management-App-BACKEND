package com.happyfit.happyfit.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.Goal;
import com.happyfit.happyfit.models.Profile;
import com.happyfit.happyfit.models.dto.ProfileDto;
import com.happyfit.happyfit.models.enums.CurrentGoalEnum;
import com.happyfit.happyfit.models.enums.GenderEnum;
import com.happyfit.happyfit.repositories.ProfileRepository;

import jakarta.validation.Valid;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private GoalService goalService;

    public Profile findById(Integer id) {
        Optional<Profile> profile = profileRepository.findById(id);
        return profile.orElse(null);
    }

    @Transactional
    public Profile create(Profile profile) {

        Goal goal = generateGoal(profile, null);

        goal = goalService.create(goal);

        // Atribui a meta ao perfil
        profile.setGoal(goal);

        profile = profileRepository.save(profile);
        return profile;
    }

    @Transactional
    public Profile update(Profile profile, Integer goalId) {

        Goal goal = generateGoal(profile, goalId);

        goal = goalService.update(goal);

        // Atribui a meta ao perfil
        profile.setGoal(goal);

        profile = profileRepository.save(profile);
        return profile;
    }

    @Transactional
    public void delete(Profile profile) {
        Goal goal = profile.getGoal();
        if (goal != null) {
            goalService.delete(goal.getId());
        }
        profileRepository.delete(profile);
    }

    public Profile fromDto(@Valid ProfileDto profileDto) {
        Profile profile = new Profile();

        profile.setWeight(profileDto.getWeight());
        profile.setHeight(profileDto.getHeight());
        profile.setAge(profileDto.getAge());
        profile.addGender(profileDto.getGender());
        profile.setHip(profileDto.getHip());
        profile.setWaist(profileDto.getWaist());
        profile.setNeck(profileDto.getNeck());
        profile.addCurrentGoal(profileDto.getCurrentGoal());
        profile.setPal(profileDto.getPal());

        Goal goal = new Goal();
        goal.setCaloricIntake(profileDto.getCaloricIntake());
        goal.setProteinIntake(profileDto.getProteinIntake());
        goal.setFatIntake(profileDto.getFatIntake());
        goal.setCarbIntake(profileDto.getCarbIntake());

        profile.setGoal(goal);

        return profile;
    }



    public Goal generateGoal(Profile profile, Integer goalId) {

        // Calcula a taxa metabólica basal, baseada na média entre as fórmulas de Mifflin-St Jeor, Harris-Benedict e Katch-McArdle
        double bmr = calculateBMR(profile);

        double caloricExpenditure = bmr * profile.getPal();

        // Ingestão calórica ideal de acordo com o objetivo
        double caloricIntake = adjustCaloricIntakeForGoal(caloricExpenditure, profile.getCurrentGoal());

        // Calcula os macronutrientes ideais
        Map<String, Float> macros = calculateMacros((float) caloricIntake, profile.getWeight());

        // Criação da meta
        Goal goal = new Goal();
        goal.setId(goalId);
        goal.setCaloricIntake((float) caloricIntake);
        goal.setProteinIntake(macros.get("protein"));
        goal.setFatIntake(macros.get("fat"));
        goal.setCarbIntake(macros.get("carbs"));

        return goal;
    }

    private double calculateBMR(Profile profile) {
        return (calculateJeorBMR(profile) + calculateHarrisBMR(profile) + calculateKatchBMR(profile)) / 3;
    }

    private double calculateJeorBMR(Profile profile) {
        double weight = profile.getWeight();
        double height = profile.getHeight();
        double age = profile.getAge();

        if (profile.getGender() == GenderEnum.MASCULINO) return 10 * weight + 6.25 * height - 5 * age + 5;
        else return 10 * weight + 6.25 * height - 5 * age - 161;
    }

    private double calculateHarrisBMR(Profile profile) {
        double weight = profile.getWeight();
        double height = profile.getHeight();
        double age = profile.getAge();

        if (profile.getGender() == GenderEnum.MASCULINO) return 13.397 * weight + 4.799 * height - 5.677 * age + 88.362;
        else return 9.247 * weight + 3.098 * height - 4.330 * age + 447.593;
    }

    private double calculateKatchBMR(Profile profile) {
        double weight = profile.getWeight();
        double height = profile.getHeight();
        double neck = profile.getNeck();
        double waist = profile.getWaist();
        double fatPercentage;

        if (profile.getGender() == GenderEnum.MASCULINO)
            fatPercentage = (495 / (1.0324 - 0.19077 * Math.log10(waist - neck) + 0.15456 * Math.log10(height))) - 450;

        else {
            double hip = profile.getHip();
            fatPercentage = (495 / (1.29579 - 0.35004 * Math.log10(waist + hip - neck) + 0.22100 * Math.log10(height))) - 450;
        }

        return 370 + (21.6 * weight * (1 - fatPercentage / 100));
    }

    // Ingestão calórica diária ideal de acordo com o objetivo
    private double adjustCaloricIntakeForGoal(double bmr, CurrentGoalEnum goal) {
        switch (goal) {
            case HIPERTROFIA:
                return bmr + 500;
            case EMAGRECIMENTO:
                return bmr - 500;
            case MANUTENCAO:
            default:
                return bmr;
        }
    }

    private Map<String, Float> calculateMacros(float caloricIntake, float weight) {
        float protein = 1.8f * weight;
        float fat = (caloricIntake - protein * 4) / 2 / 9;
        float carbs = (caloricIntake - (protein * 4) - (fat * 9)) / 4;

        Map<String, Float> macros = new HashMap<>();
        macros.put("protein", protein);
        macros.put("fat", fat);
        macros.put("carbs", carbs);

        return macros;
    }
}