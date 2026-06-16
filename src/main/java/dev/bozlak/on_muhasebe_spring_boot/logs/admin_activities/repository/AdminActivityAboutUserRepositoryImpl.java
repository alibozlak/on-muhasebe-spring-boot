package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminActivityAboutUserRepositoryImpl implements AdminActivityAboutUserRepository {

    private final JpaAdminActivityAboutUserRepository jpaAdminActivityAboutUserRepository;

    @Override
    public void addActivity(AdminActivityAboutUser adminActivityAboutUser) {
        this.jpaAdminActivityAboutUserRepository.save(adminActivityAboutUser);
    }
}
