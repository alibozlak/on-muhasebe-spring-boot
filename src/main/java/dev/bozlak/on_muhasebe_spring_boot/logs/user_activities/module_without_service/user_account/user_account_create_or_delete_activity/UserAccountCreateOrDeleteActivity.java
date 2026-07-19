package dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_account_create_or_delete_activities")
// For why didn't use encapsulation: Look project root folder README.md file
public class UserAccountCreateOrDeleteActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_account_activity_id")
    public Long userAccountCreateOrDeleteActivityId;

    @Column(name = "is_activity_create", nullable = false)
    public Boolean isActivityCreate;

    @Column(name = "account_id", nullable = false)
    public Long accountId;  // FK in DB

    @Column(name = "created_at", nullable = false)
    public java.time.LocalDate createdLogDate;
}
