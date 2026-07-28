package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutUser;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityAboutUserModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_user.AdminActivityAboutUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AdminActivityAboutUserServiceImpl implements AdminActivityAboutUserService {

    private final AdminActivityAboutUserRepository adminActivityAboutUserRepository;
    private final AdminActivityAboutUserMapper adminActivityMapper;

    @Override
    public void addAdminActivityAboutUser(AddAdminActivityAboutUserModel addAdminActivityAboutUserModel) {
        AdminActivityAboutUser adminActivityAboutUser
                = this.adminActivityMapper.toEntityFromItsAddModel(addAdminActivityAboutUserModel);
        adminActivityAboutUser.setCreatedAt(LocalDateTime.now());

        this.adminActivityAboutUserRepository.addActivity(adminActivityAboutUser);
    }
}
