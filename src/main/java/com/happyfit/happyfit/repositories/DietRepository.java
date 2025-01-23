package com.happyfit.happyfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happyfit.happyfit.models.Diet;

@Repository
public interface DietRepository extends JpaRepository<Diet, Integer> {

}