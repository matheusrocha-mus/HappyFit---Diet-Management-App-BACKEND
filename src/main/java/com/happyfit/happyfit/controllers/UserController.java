package com.happyfit.happyfit.controllers;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.happyfit.happyfit.models.User;
import com.happyfit.happyfit.models.dto.UserCreateDto;
import com.happyfit.happyfit.models.dto.AddNutritionistDto;
import com.happyfit.happyfit.services.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        User user = userService.findById(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<User> findByEmail(@RequestParam("email") String email) {
        User user = userService.findByEmail(email);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}/nutritionist")
    public ResponseEntity<User> findNutritionist(@PathVariable Integer id) {
        User user = userService.findById(id);
        User nutritionist = user.getNutritionist();

        return ResponseEntity.ok(nutritionist);
    }

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody UserCreateDto userDto) {
        User user = this.userService.findByEmail(userDto.getEmail());

        if (user != null) {
            return ResponseEntity.badRequest().body("Email já cadastrado.");
        }

        user = this.userService.fromDto(userDto);
        User newUser = this.userService.create(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{id}/nutritionist")
    public ResponseEntity<User> addNutritionist(@PathVariable Integer id,
            @RequestBody AddNutritionistDto addNutritionistDto) {
        User user = this.userService.findById(id);
        User nutritionist = this.userService.findById(addNutritionistDto.getNutritionistId());

        user = this.userService.addNutritionist(user, nutritionist);

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok().body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id) {
        User user = this.userService.findById(id);
        this.userService.delete(user);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    ResponseEntity<Void> update(@RequestBody UserCreateDto userDto, @PathVariable Integer id) {
        User user = this.userService.findById(id);
        user = this.userService.updateDto(userDto, user);
        user.setId(id);
        this.userService.update(user);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/count")
    public Long countUser() {
        long users = userService.countUser();

        return users;
    }

     
   @GetMapping("/role")
   public Long countComumUser(){
    long userComum = userService.countUserComum();

    return userComum;
   }
   
   @GetMapping("/nutritionist_id")
   public Long countUserWithNutritionist() {
        long userWhitNutritionist = userService.countUserWithNutritionist();

        return userWhitNutritionist;
   }
}
