package dev.bozlak.on_muhasebe_spring_boot.user.repository;

import dev.bozlak.on_muhasebe_spring_boot.user.User;

import java.util.Optional;

public interface UserRepository {

    Integer createUser(User user);
    Optional<User> findByUserName(String username);
}
