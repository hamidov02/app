package com.eTaskifyAPI.etaskify.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surName;
    private String email;
    private String password;
    private String phoneNumber;
    @Column(name = "is_admin", nullable = false)
    private boolean isAdmin;
}
