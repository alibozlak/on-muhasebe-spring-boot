package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.user_account.user_account_create_or_delete_activity;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcUserAccountCreateOrDeleteActivityRepository
        extends ListCrudRepository<UserAccountCreateOrDeleteActivity, Long> {
}
