package com.happyfit.happyfit.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.enums.RoleEnum;
import com.happyfit.happyfit.models.Diary;
import com.happyfit.happyfit.models.Meal;
import com.happyfit.happyfit.repositories.UserRepository;

import jakarta.transaction.Transactional;

import com.happyfit.happyfit.repositories.DiaryRepository;
import com.happyfit.happyfit.repositories.MealRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DiaryService {

    @Autowired
    private UserService userService;

    @Autowired
    private DiaryRepository diaryRepository;

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    public Diary findById(Integer id) {
        Optional<Diary> diary = diaryRepository.findById(id);
        return diary.orElse(null);
    }

    public Diary findLatestByUser(User user) {
        Optional<Diary> diary = diaryRepository.findLatestByUser(user);
        return diary.orElse(null);
    }

    public List<Diary> findByUser(User user) {
        return diaryRepository.findByUser(user);
    }

    public List<Diary> findAll() {
        return diaryRepository.findAll();
    }

    public Diary create(Diary diary) {
        return diaryRepository.save(diary);
    }

    @Transactional
    @Scheduled(cron = "0 0 0 * * ?")
    public void createDiaries() {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getRole() == RoleEnum.USER && user.getDiet() != null) {
                Diary diary = new Diary ();

                diary.setUser(user);
                diary.setDate(LocalDate.now());
                diary.setTotalCalories((float) 0);
                diary.setTotalProteins((float) 0);
                diary.setTotalCarbs((float) 0);
                diary.setTotalFats((float) 0);

                this.diaryRepository.save(diary);

                this.userService.addDiary(user, diary);

                List<Meal> meals = user.getDiet().getMeals();

                for (Meal meal : meals) {
                    Meal newMeal = new Meal();
                    newMeal.setDiary(diary);
                    newMeal.setName(meal.getName());
                    mealRepository.save(newMeal);
                }
            }
        }
    }

    public Diary update(Diary diary) {
        return diaryRepository.save(diary);
    }

    public void delete(Integer id) {
        diaryRepository.deleteById(id);
    }

    public long countDiary(){
        return diaryRepository.count();
    }

    public Long countDiariesByUserId(Long userId) {
        return diaryRepository.countDiariesByUserId(userId);
    }

    public Float sumTotalCaloriesByUserId(Long userId) {
        return diaryRepository.sumTotalCaloriesByUserId(userId);
    }

    public Float sumTotalCarbsByUserId(Long userId) {
        return diaryRepository.sumTotalCarbsByUserId(userId);
    }

    public Float sumTotalFatsByUserId(Long userId) {
        return diaryRepository.sumTotalFatsByUserId(userId);
    }

    public Float sumTotalProteinsByUserId(Long userId) {
        return diaryRepository.sumTotalProteinsByUserId(userId);
    }

    public long countDistinctDates() {
        return diaryRepository.countDistinctDates();
    }
}
