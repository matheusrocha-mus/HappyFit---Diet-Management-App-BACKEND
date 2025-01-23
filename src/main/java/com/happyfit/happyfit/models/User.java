package com.happyfit.happyfit.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.happyfit.happyfit.models.enums.RoleEnum;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "user_table")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String surname;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(nullable = false)
    private Integer role;

    public RoleEnum getRole() {
        return RoleEnum.toEnum(role);
    }

    public void addRole(Integer roleEnum) {
        this.role = roleEnum;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "diet_id", referencedColumnName = "id")
    @EqualsAndHashCode.Exclude
    private Diet diet;

    @OneToMany(mappedBy = "user")
    @JsonProperty(access = Access.READ_WRITE)
    private List<Diary> diaries = new ArrayList<Diary>();

    @ManyToOne
    @JoinColumn(name = "nutritionist_id")
    @JsonBackReference
    private User nutritionist;

    @OneToMany(mappedBy = "nutritionist")
    @JsonManagedReference
    private List<User> clients = new ArrayList<User>();

    public Integer getNutritionistId() {
        return (this.nutritionist != null) ? this.nutritionist.getId() : null;
    }
}