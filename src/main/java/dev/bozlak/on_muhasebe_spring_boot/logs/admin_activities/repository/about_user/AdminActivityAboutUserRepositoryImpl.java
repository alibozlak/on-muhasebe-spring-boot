package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_user;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminActivityAboutUserRepositoryImpl implements AdminActivityAboutUserRepository {

    private final JdbcAdminActivityAboutUserRepository jdbcAdminActivityAboutUserRepository;

    @Override
    public void addActivity(AdminActivityAboutUser adminActivityAboutUser) {
        this.jdbcAdminActivityAboutUserRepository.save(adminActivityAboutUser);
    }
}
