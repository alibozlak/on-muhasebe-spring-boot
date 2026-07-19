package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.services;

import dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity.AddUserAccountCreateOrDeleteActivityModel;
import dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity.UserAccountCreateOrDeleteActivityRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserActivityServiceImpl implements UserActivityService {

    private final UserAccountCreateOrDeleteActivityRepository userAccountCreateOrDeleteActivityRepository;

    @Override
    public void addUserAccountCreateOrDeleteActivityService(
            AddUserAccountCreateOrDeleteActivityModel addUserAccountCreateOrDeleteActivityModel
    ) {
        this.userAccountCreateOrDeleteActivityRepository.addUserAccountCreateOrDeleteActivity(
                addUserAccountCreateOrDeleteActivityModel
        );
    }
}
