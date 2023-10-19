package com.eTaskifyAPI.etaskify.controller;

import com.eTaskifyAPI.etaskify.dto.SignDto;
import com.eTaskifyAPI.etaskify.dto.AppUserDto;
import com.eTaskifyAPI.etaskify.model.AppUser;
import com.eTaskifyAPI.etaskify.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class AppUserController {
    private final AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @PostMapping("/add")
    public ResponseEntity<AppUser> signUp(@RequestBody AppUserDto user) {
        AppUser addedAppUser = appUserService.signUp(user);
        return ResponseEntity.ok(addedAppUser);
    }

    @PostMapping("/sign")
    public ResponseEntity<AppUser> kullaniciGiris(@RequestBody SignDto signDto) {
        AppUser appUser = appUserService.signUSer(signDto);
        return ResponseEntity.ok(appUser);
    }
    @PostMapping("/admin/add")
    public ResponseEntity<AppUser> addUserFromAdmin(@RequestBody AppUserDto appUserDto) {
        AppUser saveAppUser = appUserService.addUserFromAdmin(appUserDto);
        return ResponseEntity.ok(saveAppUser);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<AppUser> updateUserFromAdmin(@PathVariable Long id, @RequestBody AppUserDto appUserDto) {
        AppUser updateAppUser = appUserService.updateUser(id, appUserDto);
        return ResponseEntity.ok(updateAppUser);
    }
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return ResponseEntity.ok(appUserService.getAllUsers());
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        appUserService.deleteUser(id);
        return ResponseEntity.ok("User delete succesfully.");
    }

}
