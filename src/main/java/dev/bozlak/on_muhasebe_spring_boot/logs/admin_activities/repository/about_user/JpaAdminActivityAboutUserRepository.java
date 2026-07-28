package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_user;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAdminActivityAboutUserRepository extends JpaRepository<AdminActivityAboutUser, Long> {
}
