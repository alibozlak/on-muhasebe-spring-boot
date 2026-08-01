package dev.bozlak.on_muhasebe_spring_boot.user.repository;

import dev.bozlak.on_muhasebe_spring_boot.user.User;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.UserIdAndIsAdminModel;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JdbcUserRepository extends ListCrudRepository<User, Integer> {

    @Query("SELECT * FROM users WHERE username = :username AND is_active = true")
    Optional<User> findByUsername(@Param("username") String username);

    /**
     * Projected straight onto UserIdAndIsAdminModel: its single constructor is the
     * persistence creator and the column names map onto its properties, so the JPQL
     * "SELECT new ..." constructor expression is no longer needed.
     */
    @Query("SELECT user_id, is_admin FROM users WHERE username = :username AND is_active = true")
    Optional<UserIdAndIsAdminModel> getModelForJwtTokenGenerated(@Param("username") String username);

    @Query("SELECT hashed_password FROM users WHERE user_id = :userId AND is_active = true")
    Optional<String> getHashedPasswordByUserId(@Param("userId") Integer userId);

    /**
     * clearAutomatically is gone with JPA: Spring Data JDBC has no persistence context
     * to clear, so the next read always hits the database anyway.
     */
    @Modifying
    @Query("UPDATE users SET hashed_password = :newHashedPassword WHERE user_id = :userId")
    void changePasswordByUserId(
            @Param("userId") Integer userId, @Param("newHashedPassword") String newHashedPassword
    );

}
