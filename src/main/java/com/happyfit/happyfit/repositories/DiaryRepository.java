package com.happyfit.happyfit.repositories;

import java.util.Comparator;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.Diary;
import java.util.Optional;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Integer> {
    List<Diary> findByUser(User user);

    long count();

    @Query("SELECT COUNT(d) FROM Diary d WHERE d.user.id = :user")
    Long countDiariesByUserId(@Param("user") Long user);

    @Query("SELECT SUM(d.totalCalories) FROM Diary d WHERE d.user.id = :user")
    Float sumTotalCaloriesByUserId(@Param("user") Long user);

    @Query("SELECT SUM(d.totalCarbs) FROM Diary d WHERE d.user.id = :user")
    Float sumTotalCarbsByUserId(@Param("user") Long user);

    @Query("SELECT SUM(d.totalFats) FROM Diary d WHERE d.user.id = :user")
    Float sumTotalFatsByUserId(@Param("user") Long user);

    @Query("SELECT SUM(d.totalProteins) FROM Diary d WHERE d.user.id = :user")
    Float sumTotalProteinsByUserId(@Param("user") Long user);

    @Query("SELECT COUNT(DISTINCT v.date) FROM Diary v")
    long countDistinctDates();

    default Optional<Diary> findLatestByUser(User user) {
        List<Diary> diaries = findByUser(user);
        return diaries.stream().max(Comparator.comparing(Diary::getDate));
    }
}
