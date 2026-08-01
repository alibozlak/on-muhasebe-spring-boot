package dev.bozlak.on_muhasebe_spring_boot.user.repository;

import dev.bozlak.on_muhasebe_spring_boot.user.User;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel;
import dev.bozlak.on_muhasebe_spring_boot.user.exceptions.UserIdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcUserRepository jdbcUserRepository;

    @Override
    public Integer createUser(User user) {
        return this.jdbcUserRepository.save(user).getUserId();
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return this.jdbcUserRepository.findByUsername(username);
    }

    @Override
    public Optional<UserIdAndIsAdminModel> getModelForJwtTokenGenerated(String username) {
        return this.jdbcUserRepository.getModelForJwtTokenGenerated(username);
    }

    @Override
    public String getHashedPasswordByUserId(Integer userId) {
        return this.jdbcUserRepository.getHashedPasswordByUserId(userId)
                .orElseThrow(() -> new UserIdNotFoundException(userId));
    }

    @Override
    public void changePasswordByUserId(Integer userId, String newHashedPassword) {
        this.jdbcUserRepository.changePasswordByUserId(userId, newHashedPassword);
    }
}
