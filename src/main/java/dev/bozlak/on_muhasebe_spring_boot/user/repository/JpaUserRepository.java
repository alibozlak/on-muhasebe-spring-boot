package dev.bozlak.on_muhasebe_spring_boot.user.repository;

import dev.bozlak.on_muhasebe_spring_boot.user.User;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = :username AND u.isActive = true")
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT new dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel(" +
            "u.userId, u.isAdmin" +
            ") FROM User u WHERE u.username = :username AND u.isActive = true"
    )
    Optional<UserIdAndIsAdminModel> getModelForJwtTokenGenerated(@Param("username") String username);

    @Query("SELECT u.hashedPassword FROM User u WHERE u.userId = :userId AND u.isActive = true")
    Optional<String> getHashedPasswordByUserId(@Param("userId") Integer userId);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE User u SET u.hashedPassword = :newHashedPassword WHERE u.userId = :userId")
    void changePasswordByUserId(
            @Param("userId") Integer userId, @Param("newHashedPassword") String newHashedPassword
    );

}
