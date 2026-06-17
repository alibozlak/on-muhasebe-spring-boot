package dev.bozlak.on_muhasebe_spring_boot.user.service;

import dev.bozlak.on_muhasebe_spring_boot.admin.Admin;
import dev.bozlak.on_muhasebe_spring_boot.user.User;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.CreateUserRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel;
import dev.bozlak.on_muhasebe_spring_boot.user.repository.UserRepository;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.AddAdminActivityModel;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.AdminActivityService;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.AdminActivityType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminActivityService adminActivityService;

    @Override
    @Transactional
    public void createUser(CreateUserRequestDto createUserRequestDto, Short adminId) {
        User user = this.userMapper.toEntityFromItsCreateRequestDto(createUserRequestDto);
        String hashedPassword = this.passwordEncoder.encode(createUserRequestDto.getPassword());
        user.setHashedPassword(hashedPassword);
        user.setCreatedAt(LocalDate.now());
        user.setWhoCreatedAdmin(new Admin(adminId, null, null, null));
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
}
