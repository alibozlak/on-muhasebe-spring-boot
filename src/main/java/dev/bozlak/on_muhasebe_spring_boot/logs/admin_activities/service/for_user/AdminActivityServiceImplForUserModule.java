package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service.for_user;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityAboutUserModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service.AdminActivityAboutUserService;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.AddAdminActivityModel;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.AdminActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminActivityServiceImplForUserModule implements AdminActivityService {

    private final AdminActivityAboutUserService adminActivityAboutUserService;
    private final AdminActivityMapperForUser adminActivityMapperForUser;

    @Override
    public void addAdminActivity(AddAdminActivityModel addAdminActivityModel) {
        AddAdminActivityAboutUserModel addAdminActivityAboutUserModel
                = this.adminActivityMapperForUser.toAdminActivityModuleFromUserModule(addAdminActivityModel);

        this.adminActivityAboutUserService.addAdminActivityAboutUser(addAdminActivityAboutUserModel);
    }
}
