package dev.bozlak.on_muhasebe_spring_boot.account.repository;

import dev.bozlak.on_muhasebe_spring_boot.account.Account;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;
import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AccountMapperForRepositoryTest {

//    @Autowired
    private final AccountMapperForRepository accountMapperForRepository
        = new AccountMapperForRepositoryImpl();

    @Test
    public void mapperShouldConvertFromCreateAccountRequestModelToAccountEntity(){
        java.time.LocalDate now = java.time.LocalDate.now();

        CreateAccountRequestModel createAccountRequestModel = new CreateAccountRequestModel(
                12, "Ziraat Banka 1", now, false, new BigDecimal(20_000)
        );

        Account account = this.accountMapperForRepository.toEntityFromItsCreateModel(createAccountRequestModel);

        assertEquals(12, account.getUserId());
        assertEquals(createAccountRequestModel.getAmount(), account.getAmount());
        assertEquals(createAccountRequestModel.getCreatedAt(), account.getCreatedAt());
        assertEquals("Ziraat Banka 1", account.getAccountName());
//        assertEquals(false, account.getIsCashAccount());
        assertFalse(account.getIsCashAccount());
    }
}
