package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.service;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.dtos.AddUserActivityModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.repository.UserHimselfActivityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHimselfActivityServiceImpl implements UserHimselfActivityService {

    private final UserHimselfActivityRepository userHimselfActivityRepository;

    @Override
    public void addUserHimselfActivity(AddUserActivityModel addUserActivityModel) {
        this.userHimselfActivityRepository.addUserAboutHimselfActivity(addUserActivityModel);
    }
}
