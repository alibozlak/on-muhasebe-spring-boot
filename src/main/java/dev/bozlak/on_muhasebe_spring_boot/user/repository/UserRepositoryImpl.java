package dev.bozlak.on_muhasebe_spring_boot.user.repository;

import dev.bozlak.on_muhasebe_spring_boot.user.User;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel;
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
}
