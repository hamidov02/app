package com.eTaskifyAPI.etaskify.service;

import com.eTaskifyAPI.etaskify.dto.SignDto;
import com.eTaskifyAPI.etaskify.dto.AppUserDto;
import com.eTaskifyAPI.etaskify.exception.EmailAlreadyExistException;
import com.eTaskifyAPI.etaskify.exception.PasswordMistakeException;
import com.eTaskifyAPI.etaskify.exception.UserNotFoundException;
import com.eTaskifyAPI.etaskify.model.AppUser;
import com.eTaskifyAPI.etaskify.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser signUp(AppUserDto user) {
        if (appUserRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException("This email already exist");
        }
        AppUser newAppUser = new AppUser();
        newAppUser.setName(user.getName());
        newAppUser.setSurName(user.getSurName());
        newAppUser.setEmail(user.getEmail());
        newAppUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newAppUser.setPhoneNumber(user.getPhoneNumber());
        return appUserRepository.save(newAppUser);
    }

    public AppUser signUSer(SignDto signDto) {
        Optional<AppUser> userOptional = appUserRepository.findByEmail(signDto.getEmail());
        if (!userOptional.isPresent()) {
            throw new EmailAlreadyExistException("Email dont found!");
        }
        AppUser appUser = userOptional.get();
        if (!passwordEncoder.matches(signDto.getPassword(), appUser.getPassword())) {
            throw new PasswordMistakeException("Password is mistake");
        }
        return appUser;
    }

    public AppUser addUserFromAdmin(AppUserDto appUserDto) {
        AppUser newAppUser = new AppUser();
        newAppUser.setAdmin(appUserDto.isAdmin());
        return appUserRepository.save(newAppUser);
    }

    public AppUser updateUser(Long id, AppUserDto appUserDto) {
        AppUser existedAppUser = appUserRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found!"));

        existedAppUser.setName(appUserDto.getName());
        existedAppUser.setSurName(appUserDto.getSurName());
        existedAppUser.setEmail(appUserDto.getEmail());
        if (appUserDto.getExistedPassword() != null && appUserDto.getNewPassword() != null) {
            if (!passwordEncoder.matches(appUserDto.getExistedPassword(), existedAppUser.getPassword())) {
                throw new PasswordMistakeException("ExistedPassword is mistake!");
            }
            existedAppUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));
        }
        existedAppUser.setAdmin(appUserDto.isAdmin());

        return appUserRepository.save(existedAppUser);
    }

    public List<AppUser> getAllUsers() {
        return appUserRepository.findAll();
    }

    public void deleteUser(Long id) {
        if (!appUserRepository.existsById(id)) {
            throw new UserNotFoundException("User Not Found!");
        }
        appUserRepository.deleteById(id);
    }
}
