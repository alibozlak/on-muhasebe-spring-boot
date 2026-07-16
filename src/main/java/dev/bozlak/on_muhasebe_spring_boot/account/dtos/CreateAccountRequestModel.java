package dev.bozlak.on_muhasebe_spring_boot.account.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class CreateAccountRequestModel {

    private Integer userId;
    private String accountName;
    private java.time.LocalDate createdAt;
    private Boolean isCashAccount;
    private BigDecimal amount;
}
