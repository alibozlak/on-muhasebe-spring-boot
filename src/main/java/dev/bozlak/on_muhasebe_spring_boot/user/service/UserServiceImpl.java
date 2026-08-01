package dev.bozlak.on_muhasebe_spring_boot.user.service;

import dev.bozlak.on_muhasebe_spring_boot.admin.Admin;
import dev.bozlak.on_muhasebe_spring_boot.user.User;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.ChangePasswordRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.CreateUserRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel;
import dev.bozlak.on_muhasebe_spring_boot.user.exceptions.PasswordIncorrectException;
import dev.bozlak.on_muhasebe_spring_boot.user.repository.UserRepository;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminActivityService adminActivityService;
    private final UserActivityService userActivityService;

    @Override
    @Transactional
    public void createUser(CreateUserRequestDto createUserRequestDto, Short adminId) {
        User user = this.userMapper.toEntityFromItsCreateRequestDto(createUserRequestDto);
        String hashedPassword = this.passwordEncoder.encode(createUserRequestDto.getPassword());
        user.setHashedPassword(hashedPassword);
        user.setCreatedAt(LocalDate.now());
        user.setWhoCreatedAdminId(adminId);
        Integer createdUserId = this.userRepository.createUser(user);

        AddAdminActivityModel addAdminActivityModel = new AddAdminActivityModel(
                adminId, AdminActivityType.CREATED_USER_BY_ADMIN.name(), createdUserId
        );
        this.adminActivityService.addAdminActivity(addAdminActivityModel);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return this.userRepository.findByUserName(username);
    }

    @Override
    public UserIdAndIsAdminModel getModelForJwtTokenGenerated(String username) {
        return this.userRepository.getModelForJwtTokenGenerated(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    @Transactional
    public void changePassword(ChangePasswordRequestDto changePasswordRequestDto, Integer userId) {
        String hashedCurrentPassword = this.userRepository.getHashedPasswordByUserId(userId);
        if (this.passwordEncoder.matches(changePasswordRequestDto.getCurrentPassword(), hashedCurrentPassword)){
            String newHashedPassword = this.passwordEncoder.encode(changePasswordRequestDto.getNewPassword());
            this.userRepository.changePasswordByUserId(userId, newHashedPassword);

            AddUserActivityModel addUserActivityModel = new AddUserActivityModel(
                    userId, UserActivityType.USER_CHANGED_PASSWORD.name()
            );
            this.userActivityService.addUserHimselfActivity(addUserActivityModel);
        } else {
            throw new PasswordIncorrectException();
        }
    }


}
