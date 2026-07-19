package dev.bozlak.on_muhasebe_spring_boot.account.api;

import dev.bozlak.core.responses.ResponseBody;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.account.exceptions.UserAccountDidNotCreateException;
import dev.bozlak.on_muhasebe_spring_boot.account.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
@lombok.AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    public ResponseEntity<ResponseBody> createAccount(
            @Valid @RequestBody CreateAccountRequestDto createAccountRequestDto,
            @RequestAttribute(name = "userId") Integer userId
    ){
        try {
            this.accountService.createAccount(createAccountRequestDto, userId);
            return new ResponseEntity<>(
                    new ResponseBody(true),
                    HttpStatus.CREATED
            );
        } catch (Exception e){
            throw  new UserAccountDidNotCreateException(e.getMessage());
        }
    }
}
