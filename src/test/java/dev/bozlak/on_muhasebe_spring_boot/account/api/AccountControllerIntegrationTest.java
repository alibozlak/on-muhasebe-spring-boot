package dev.bozlak.on_muhasebe_spring_boot.account.api;

import dev.bozlak.on_muhasebe_spring_boot.util.BaseIntegrationTest;
import tools.jackson.databind.ObjectMapper;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.account.repository.JpaAccountRepository;
import dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .user_account
        .user_account_create_or_delete_activity.JpaUserAccountCreateOrDeleteActivityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JpaAccountRepository jpaAccountRepository;

    @Autowired
    JpaUserAccountCreateOrDeleteActivityRepository jpaUserAccountCreateOrDeleteActivityRepository;

    @Test
    void createAccount_persistsAccountAndLog_andReturns201() throws Exception {
        final int userId = 12;
        CreateAccountRequestDto createAccountRequestDto = new CreateAccountRequestDto(
                "Yapı Kredi banka hesabı 2", false, new BigDecimal("12000")
        );

        mockMvc.perform(post("/api/v1/accounts/create-account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(createAccountRequestDto))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        // assert 1: accounts table insert record
        assertThat(this.jpaAccountRepository.findAll())
                .singleElement()
                .satisfies(account -> {
                    assertThat(account.getUserId()).isEqualTo(userId);
                    assertThat(account.getAccountName()).isEqualTo("Yapı Kredi banka hesabı 2");
                    assertThat(account.getIsCashAccount()).isFalse();
                    assertThat(account.getIsActive()).isTrue();
                    assertThat(account.getAmount()).isEqualByComparingTo("12000");
                });

        // assert 2: Did inserted record user_account_create_or_delete_activities table
        assertThat(this.jpaUserAccountCreateOrDeleteActivityRepository.findAll())
                .singleElement()
                .satisfies(log -> {
                    assertThat(log.isActivityCreate).isTrue();
                    assertThat(log.accountId).isNotNull();
                    assertThat(log.userAccountCreateOrDeleteActivityId).isNotNull();
                    assertThat(log.createdLogDate).isBeforeOrEqualTo(LocalDate.now());
                });
    }
}
