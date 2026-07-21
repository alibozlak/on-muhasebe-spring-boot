package dev.bozlak.on_muhasebe_spring_boot.account.service;

import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountMapperForServiceTest {

    private final AccountMapperForService accountMapperForService
            = new AccountMapperForServiceImpl();

    @Test
    void toModelFromDto_mapperShouldConvertFromCreateAccountRequestDtoToModel(){
        CreateAccountRequestDto createAccountRequestDto = new CreateAccountRequestDto(
                "YapıKredi banka hesabı 1", false, new BigDecimal(10_000)
        );

        CreateAccountRequestModel createAccountRequestModel
                = this.accountMapperForService.toModelFromDto(createAccountRequestDto);

        assertEquals("YapıKredi banka hesabı 1", createAccountRequestModel.getAccountName());
        assertEquals(
                createAccountRequestDto.getAmount().doubleValue(),
                createAccountRequestModel.getAmount().doubleValue()
        );
    }
}
