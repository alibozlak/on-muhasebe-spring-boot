package dev.bozlak.on_muhasebe_spring_boot.user.service;

import dev.bozlak.on_muhasebe_spring_boot.user.User;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.ChangePasswordRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.CreateUserRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel;

import java.util.Optional;

public interface UserService {

    void createUser(CreateUserRequestDto createUserRequestDto, Short adminId);
    Optional<User> getUserByUsername(String username);
    UserIdAndIsAdminModel getModelForJwtTokenGenerated(String username);
    void changePassword(ChangePasswordRequestDto changePasswordRequestDto, Integer userId);

}
