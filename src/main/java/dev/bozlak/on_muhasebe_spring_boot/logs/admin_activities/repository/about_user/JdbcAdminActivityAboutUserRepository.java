package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_user;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutUser;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcAdminActivityAboutUserRepository extends ListCrudRepository<AdminActivityAboutUser, Long> {
}
