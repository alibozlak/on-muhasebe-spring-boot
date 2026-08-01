package dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_account_create_or_delete_activities")
// For why didn't use encapsulation: Look project root folder README.md file
public class UserAccountCreateOrDeleteActivity {

    @Id
    @Column("user_account_activity_id")
    public Long userAccountCreateOrDeleteActivityId;

    @Column("is_activity_create")
    public Boolean isActivityCreate;

    @Column("account_id")
    public Long accountId;  // Logical ref to accounts.account_id; no FK constraint in the DB

    @Column("created_at")
    public java.time.LocalDate createdLogDate;
}
