package dev.bozlak.on_muhasebe_spring_boot.account.repository;

import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;

public interface AccountRepository {

    Long createAccount(CreateAccountRequestModel createAccountRequestModel);
}
