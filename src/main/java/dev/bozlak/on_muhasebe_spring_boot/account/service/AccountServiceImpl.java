package dev.bozlak.on_muhasebe_spring_boot.account.service;

import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;
import dev.bozlak.on_muhasebe_spring_boot.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapperForService accountMapperForService;

    @Override
    @Transactional // Not event-publisher-listener async architecture.
    public void createAccount(CreateAccountRequestDto createAccountRequestDto, Integer userId) {
        CreateAccountRequestModel createAccountRequestModel
                = this.accountMapperForService.toModelFromDto(createAccountRequestDto);
        createAccountRequestModel.setUserId(userId);
        createAccountRequestModel.setCreatedAt(java.time.LocalDate.now());

        this.accountRepository.createAccount(createAccountRequestModel);


        // ToDo : I will add logging
    }
}
