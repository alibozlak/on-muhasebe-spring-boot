package dev.bozlak.on_muhasebe_spring_boot.account.service;

import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;
import dev.bozlak.on_muhasebe_spring_boot.account.repository.AccountRepository;
import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.user_account.user_account_create_or_delete_activity.AddUserAccountCreateOrDeleteActivityModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.services.UserActivityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    private final AccountMapperForService accountMapperForService = new AccountMapperForServiceImpl();

    @Mock
    private UserActivityService userActivityService;

    private AccountServiceImpl accountServiceImpl;

    @BeforeEach
    void injectDependencies(){
        this.accountServiceImpl = new AccountServiceImpl(
                this.accountRepository, this.accountMapperForService, this.userActivityService
        );
    }

    @Test
    void createAccount_callRepositorysMethodAndItReturnedLongTypedAccountId_and_methodShouldCallLogService(){
        CreateAccountRequestDto createAccountRequestDto = new CreateAccountRequestDto(
                "Yapı Kredi banka hesabı 2", false, new BigDecimal(12_000)
        );

        this.accountServiceImpl.createAccount(createAccountRequestDto, 12);

        // Assert 1 :
        verify(accountRepository, times(1)).createAccount(any(CreateAccountRequestModel.class));
        // Assert 2 :
        verify(userActivityService, times(1)).addUserAccountCreateOrDeleteActivityService(
                any(AddUserAccountCreateOrDeleteActivityModel.class)
        );
    }
}
