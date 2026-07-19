package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.repository;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.dtos.AddUserActivityModel;

public interface UserHimselfActivityRepository {

    void addUserAboutHimselfActivity(AddUserActivityModel addUserActivityModel);
}
