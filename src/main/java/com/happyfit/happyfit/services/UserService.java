package com.happyfit.happyfit.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.Diary;
import com.happyfit.happyfit.models.Diet;
import com.happyfit.happyfit.models.Profile;
import com.happyfit.happyfit.models.dto.UserCreateDto;
import com.happyfit.happyfit.models.enums.RoleEnum;
import com.happyfit.happyfit.repositories.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElse(null);
    }

    public User findById(Integer id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElse(null);
    }

    public boolean verifyLogin(User user, String password) {
        String userPassword = user.getPassword();
        boolean isPasswordCorrect = bCryptPasswordEncoder.matches(password, userPassword);
        return isPasswordCorrect;
    }

    @Transactional
    public User create(User user) {
        user.setId(null);
        user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
        user = this.userRepository.save(user);
        return user;
    }

    public User delete(User user) {
        for (User client : user.getClients()) {
            client.setNutritionist(null);
        }

        this.userRepository.delete(user);
        return user;
    }

    public User update(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        return user;
    }

    public User addNutritionist(User user, User nutritionist) {
        if (user.getRole() == RoleEnum.USER && nutritionist.getRole() == RoleEnum.NUTRITIONIST) {
            user.setNutritionist(nutritionist);
            this.userRepository.save(user);
            return user;
        }

        return null;
    }

    public User addProfile(User user, Profile profile) {
        user.setProfile(profile);
        this.userRepository.save(user);
        return user;
    }

    public User addDiet(User user, Diet diet) {
        user.setDiet(diet);
        this.userRepository.save(user);
        return user;
    }

    public User addDiary(User user, Diary diary) {
        user.getDiaries().add(diary);
        this.userRepository.save(user);
        return user;
    }

    public User fromDto(@Valid UserCreateDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.addRole(userDto.getRole());
        return user;
    }

    public User updateDto(@Valid UserCreateDto userDto, User user) {
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.addRole(userDto.getRole());
        return user;
    }

    public long countUser(){
        return userRepository.count();
    }

    public long countUserComum(){
        return userRepository.countUser();
    }

    public long countUserWithNutritionist(){
        return userRepository.countUSerWidthNutritionist();
    }
}