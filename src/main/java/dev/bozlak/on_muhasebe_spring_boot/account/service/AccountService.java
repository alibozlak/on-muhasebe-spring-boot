package dev.bozlak.on_muhasebe_spring_boot.account.service;

import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;

public interface AccountService {

    void createAccount(CreateAccountRequestDto createAccountRequestDto, Integer userId);
}
