package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.service.for_user_module;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.service.UserHimselfActivityService;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.AddUserActivityModel;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHimselfActivityServiceImplForUserModule implements UserActivityService {

    private final UserHimselfActivityService userHimselfActivityService;
    private final Mapper mapper;

    @Override
    public void addUserHimselfActivity(AddUserActivityModel addUserActivityModel) {
        dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.dtos.AddUserActivityModel addUserActivityModelForLogModule
                = this.mapper.toAddLogModuleModelFromAddUserModuleModel(addUserActivityModel);
        addUserActivityModelForLogModule.setCreatedAt(java.time.LocalDate.now());

        this.userHimselfActivityService.addUserHimselfActivity(addUserActivityModelForLogModule);
    }
}
