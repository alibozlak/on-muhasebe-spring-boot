package dev.bozlak.on_muhasebe_spring_boot.account.dtos;

import dev.bozlak.on_muhasebe_spring_boot.account.exceptions.CashAmountValueCantBeNegativeException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateAccountRequestDto {

    @NotNull
    @NotBlank
    private String accountName;

    @NotNull
    private Boolean isCashAccount;

    @NotNull
    private BigDecimal amount;

    public CreateAccountRequestDto(String accountName, Boolean isCashAccount, BigDecimal amount) {
        if (isCashAccount && amount.doubleValue() < 0){
            throw new CashAmountValueCantBeNegativeException(amount);
        }

        this.accountName = accountName;
        this.isCashAccount = isCashAccount;
        this.amount = amount;
    }
}
