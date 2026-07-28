package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_user;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutUser;

public interface AdminActivityAboutUserRepository {

    void addActivity(AdminActivityAboutUser adminActivityAboutUser);
}
