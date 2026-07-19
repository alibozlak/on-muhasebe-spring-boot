package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.user_account.mappers;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.user_account.user_account_create_or_delete_activity.AddUserAccountCreateOrDeleteActivityModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.user_account.user_account_create_or_delete_activity.UserAccountCreateOrDeleteActivity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserAccountMapper {

    UserAccountCreateOrDeleteActivity toEntityFromItsAddOrDeleteModel(
            AddUserAccountCreateOrDeleteActivityModel addUserAccountCreateOrDeleteActivityModel
    );

}
