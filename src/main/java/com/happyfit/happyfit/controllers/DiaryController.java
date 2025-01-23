package com.happyfit.happyfit.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.happyfit.happyfit.models.Diary;
import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.services.DiaryService;
import com.happyfit.happyfit.services.UserService;

@RestController
@RequestMapping("/user/{userId}/diary")
public class DiaryController {

    @Autowired
    private DiaryService diaryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Diary>> getUserDiaries(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        if (user == null) return ResponseEntity.notFound().build();
        List<Diary> userDiaries = diaryService.findByUser(user);
        return ResponseEntity.ok(userDiaries);
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<Diary> getDiaryById(@PathVariable Integer userId, @PathVariable Integer diaryId) {
        Diary diary = diaryService.findById(diaryId);
        if (diary == null || !diary.getUser().getId().equals(userId)) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(diary);
    }

    @GetMapping("/latest")
    public ResponseEntity<Diary> getLatestDiary(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Diary latestDiary = diaryService.findLatestByUser(user);
        if (latestDiary == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(latestDiary);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diary> createDiary(@PathVariable Integer userId, @RequestBody Diary diary) {
        User user = userService.findById(userId);

        if (user == null) return ResponseEntity.notFound().build();

        diary.setUser(user);
        diary.setDate(LocalDate.now());
        Diary newDiary = diaryService.create(diary);

        user = this.userService.addDiary(user, newDiary);

        return ResponseEntity.ok(newDiary);
    }

    @PutMapping(value = "/{diaryId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Diary> updateDiary(@PathVariable Integer userId, @PathVariable Integer diaryId, @RequestBody Diary updatedDiary) {
        Diary existingDiary = diaryService.findById(diaryId);
        if (existingDiary == null || !existingDiary.getUser().getId().equals(userId)) return ResponseEntity.notFound().build();
        updatedDiary.setId(diaryId);
        updatedDiary.setUser(existingDiary.getUser());
        updatedDiary.setDate(existingDiary.getDate());
        Diary savedDiary = diaryService.update(updatedDiary);
        return ResponseEntity.ok(savedDiary);
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<Void> deleteDiary(@PathVariable Integer userId, @PathVariable Integer diaryId) {
        Diary diary = diaryService.findById(diaryId);
        if (diary == null || !diary.getUser().getId().equals(userId)) return ResponseEntity.notFound().build();
        diaryService.delete(diaryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public Long countDiariesByUserId(@PathVariable Long userId) {
        Long total = diaryService.countDiariesByUserId(userId);
        return total;
    }

    @GetMapping("/totalCalories")
    public Float sumTotalCaloriesByUserId(@PathVariable Long userId) {
        float total = diaryService.sumTotalCaloriesByUserId(userId);
        return total;
    }

    @GetMapping("/totalCarbs")
    public Float sumTotalCarbsByUserId(@PathVariable Long userId) {
        float total = diaryService.sumTotalCarbsByUserId(userId);
        return total;
    }

    @GetMapping("/totalFats")
    public Float sumTotalFatsByUserId(@PathVariable Long userId) {
        float total = diaryService.sumTotalFatsByUserId(userId);
        return total;
    }

    @GetMapping("/totalProteins")
    public Float sumTotalProteinsByUserId(@PathVariable Long userId) {
        float total = diaryService.sumTotalProteinsByUserId(userId);
        return total;

    }
    
}
