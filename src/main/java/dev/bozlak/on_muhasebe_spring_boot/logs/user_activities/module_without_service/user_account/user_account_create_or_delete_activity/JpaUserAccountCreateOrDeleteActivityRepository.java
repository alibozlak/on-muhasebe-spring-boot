package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.user_account.user_account_create_or_delete_activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserAccountCreateOrDeleteActivityRepository
        extends JpaRepository<UserAccountCreateOrDeleteActivity, Long> {
}
