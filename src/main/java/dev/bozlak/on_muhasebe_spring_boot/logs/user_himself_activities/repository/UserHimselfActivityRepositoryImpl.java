package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.repository;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.UserHimselfActivity;
import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.dtos.AddUserActivityModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserHimselfActivityRepositoryImpl implements UserHimselfActivityRepository {

    private final JpaUserHimselfActivityRepository jpaUserHimselfActivityRepository;
    private final UserHimselfActivityMapper mapper;

    @Override
    public void addUserAboutHimselfActivity(AddUserActivityModel addUserActivityModel) {
        UserHimselfActivity userHimselfActivity = this.mapper.toEntityFormItsAddModel(addUserActivityModel);
        this.jpaUserHimselfActivityRepository.save(userHimselfActivity);
    }
}
