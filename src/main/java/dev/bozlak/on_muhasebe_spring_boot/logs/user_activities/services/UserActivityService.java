package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.services;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.user_account.user_account_create_or_delete_activity.AddUserAccountCreateOrDeleteActivityModel;

public interface UserActivityService {

    void addUserAccountCreateOrDeleteActivityService(
            AddUserAccountCreateOrDeleteActivityModel addUserAccountCreateOrDeleteActivityModel
    );

}
