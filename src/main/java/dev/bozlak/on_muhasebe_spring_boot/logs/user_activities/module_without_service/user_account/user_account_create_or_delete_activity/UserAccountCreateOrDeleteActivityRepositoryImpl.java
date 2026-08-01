package dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity;

import dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .mappers.UserAccountMapper;
import org.springframework.stereotype.Component;

@Component
@lombok.RequiredArgsConstructor
public class UserAccountCreateOrDeleteActivityRepositoryImpl implements UserAccountCreateOrDeleteActivityRepository {

    private final JdbcUserAccountCreateOrDeleteActivityRepository jdbcUserAccountCreateOrDeleteActivityRepository;
    private final UserAccountMapper userAccountMapper;

    @Override
    public void addUserAccountCreateOrDeleteActivity(
            AddUserAccountCreateOrDeleteActivityModel addUserAccountCreateOrDeleteActivityModel
    ) {
        UserAccountCreateOrDeleteActivity userAccountCreateOrDeleteActivity
                = this.userAccountMapper.toEntityFromItsAddOrDeleteModel(addUserAccountCreateOrDeleteActivityModel);
        userAccountCreateOrDeleteActivity.createdLogDate = java.time.LocalDate.now();

        try {
            this.jdbcUserAccountCreateOrDeleteActivityRepository.save(userAccountCreateOrDeleteActivity);
        } catch (Exception e){
            throw new UserAccountCreateOrDeleteActivityDidntAddException(e.getMessage());
        }
    }
}
