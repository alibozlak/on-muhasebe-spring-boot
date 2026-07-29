package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.services;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.contact.created_or_deleted.UserContactActivityCreatedOrDeletedRepository;
import dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities.module_without_service
        .product_or_service.create_or_delete_activity.ProductOrServiceCreateOrDeleteActivityRepository;
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
    private final ProductOrServiceCreateOrDeleteActivityRepository productOrServiceCreateOrDeleteActivityRepository;
    private final UserContactActivityCreatedOrDeletedRepository userContactActivityCreatedOrDeletedRepository;

    @Override
    public void addUserAccountCreateOrDeleteActivityService(
            AddUserAccountCreateOrDeleteActivityModel addUserAccountCreateOrDeleteActivityModel
    ) {
        this.userAccountCreateOrDeleteActivityRepository.addUserAccountCreateOrDeleteActivity(
                addUserAccountCreateOrDeleteActivityModel
        );
    }

    @Override
    public void addProductOrServiceCreateOrDeleteActivity(Long productOrServiceId) {
        this.productOrServiceCreateOrDeleteActivityRepository.add(productOrServiceId);
    }

    @Override
    public void addContactCreateOrDeleteActivity(Long contactId) {
        this.userContactActivityCreatedOrDeletedRepository.add(contactId);
    }
}
