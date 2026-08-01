package dev.bozlak.on_muhasebe_spring_boot.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    private Long accountId;

    /**
     * Logical reference to users.user_id. Nothing enforces it: the DB declares no FK
     * constraint (see README.md) and Spring Data JDBC has no @JoinColumn equivalent by
     * design.
     */
    @Column("user_id")
    private Integer userId;

    @Column("account_name")
    private String accountName;

    @Column("created_at")
    private java.time.LocalDate createdAt;

    @Column("is_cash_account")
    private Boolean isCashAccount;

    @Column("amount")
    private BigDecimal amount;

    @Column("is_active")
    private Boolean isActive;

}
