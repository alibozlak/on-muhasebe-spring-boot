package dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity;

public class UserAccountCreateOrDeleteActivityDidntAddException extends RuntimeException {
    public UserAccountCreateOrDeleteActivityDidntAddException(String message) {
        super(
                String.format(
                        "UserAccountCreateOrDeleteActivity didn't add. Check logs.user_activities" +
                        ".user_account_create_or_delete_activity package! Other message : %s",
                        message
                )
        );
    }
}
