package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.service;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.dtos.AddUserActivityModel;

public interface UserHimselfActivityService {

    void addUserHimselfActivity(AddUserActivityModel addUserActivityModel);
}
