package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.user_account.user_account_create_or_delete_activity;

public interface UserAccountCreateOrDeleteActivityRepository {

    void addUserAccountCreateOrDeleteActivity(
            AddUserAccountCreateOrDeleteActivityModel addUserAccountCreateOrDeleteActivityModel
    );

}
