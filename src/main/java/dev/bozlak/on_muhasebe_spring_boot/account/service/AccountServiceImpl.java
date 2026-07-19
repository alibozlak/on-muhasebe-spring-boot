package dev.bozlak.on_muhasebe_spring_boot.account.service;

import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;
import dev.bozlak.on_muhasebe_spring_boot.account.repository.AccountRepository;
import dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity.AddUserAccountCreateOrDeleteActivityModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.services.UserActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapperForService accountMapperForService;
    private final UserActivityService userActivityService;

    @Override
    @Transactional // Not event-publisher-listener async architecture.
    public void createAccount(CreateAccountRequestDto createAccountRequestDto, Integer userId) {
        CreateAccountRequestModel createAccountRequestModel
                = this.accountMapperForService.toModelFromDto(createAccountRequestDto);
        createAccountRequestModel.setUserId(userId);
        createAccountRequestModel.setCreatedAt(java.time.LocalDate.now());
        Long createdAccountId = this.accountRepository.createAccount(createAccountRequestModel);


        this.userActivityService.addUserAccountCreateOrDeleteActivityService(
                new AddUserAccountCreateOrDeleteActivityModel(true, createdAccountId)
        );
    }
}
