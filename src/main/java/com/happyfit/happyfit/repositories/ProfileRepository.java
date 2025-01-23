package com.happyfit.happyfit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.happyfit.happyfit.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}