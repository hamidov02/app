package com.eTaskifyAPI.etaskify.dto;

import lombok.Data;

@Data
public class AppUserDto {
    private String name;
    private String surName;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isAdmin;
    private String existedPassword;
    private String newPassword;
}
