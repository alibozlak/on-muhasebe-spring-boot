package dev.bozlak.on_muhasebe_spring_boot.account.api;

import dev.bozlak.core.responses.ResponseBody;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.account.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @Test
    void createAccount_ifCorrectDtoAndUserIdComeMethodShouldCallServiceAndReturnResponseEntity(){
        Integer userId = 12;
        CreateAccountRequestDto createAccountRequestDto = new CreateAccountRequestDto(
                "Yapı Kredi banka hesabı 2", false, new BigDecimal(12_000)
        );

        ResponseEntity<ResponseBody> response = this.accountController.createAccount(
                createAccountRequestDto, userId
        );

        // Assert 1 :
        verify(accountService, times(1)).createAccount(createAccountRequestDto, userId);
        // Assert 2 :
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
