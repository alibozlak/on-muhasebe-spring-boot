package dev.bozlak.on_muhasebe_spring_boot.account.repository;

import dev.bozlak.on_muhasebe_spring_boot.account.Account;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {

    private final JpaAccountRepository jpaAccountRepository;
    private final AccountMapperForRepository accountMapperForRepository;

    @Override
    public Long createAccount(CreateAccountRequestModel createAccountRequestModel) {
        Account account = this.accountMapperForRepository.toEntityFromItsCreateModel(createAccountRequestModel);
        account.setIsActive(true);

        return this.jpaAccountRepository.save(account).getAccountId();
    }
}
