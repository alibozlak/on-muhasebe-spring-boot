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

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Integer createUser(User user) {
        return this.jpaUserRepository.save(user).getUserId();
    }

    @Override
    public Optional<User> findByUserName(String username) {
        return this.jpaUserRepository.findByUsername(username);
    }

    @Override
    public Optional<UserIdAndIsAdminModel> getModelForJwtTokenGenerated(String username) {
        return this.jpaUserRepository.getModelForJwtTokenGenerated(username);
    }

    @Override
    public String getHashedPasswordByUserId(Integer userId) {
        return this.jpaUserRepository.getHashedPasswordByUserId(userId)
                .orElseThrow(() -> new UserIdNotFoundException(userId));
    }

    @Override
    public void changePasswordByUserId(Integer userId, String newHashedPassword) {
        this.jpaUserRepository.changePasswordByUserId(userId, newHashedPassword);
    }
}
