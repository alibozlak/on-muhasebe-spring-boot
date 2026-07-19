package dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity;

import dev.bozlak.on_muhasebe_spring_boot.logs.AddLogModel;

@lombok.AllArgsConstructor
// For why didn't use encapsulation or record: Look project root folder README.md file
public final class AddUserAccountCreateOrDeleteActivityModel extends AddLogModel {
    public final Boolean isActivityCreate;
    public final Long accountId;
}
