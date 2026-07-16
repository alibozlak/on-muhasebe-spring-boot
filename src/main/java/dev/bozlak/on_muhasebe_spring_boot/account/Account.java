package dev.bozlak.on_muhasebe_spring_boot.account;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    /**
     * Don't use @JoinColumn!!
     */
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "created_at", nullable = false)
    private java.time.LocalDate createdAt;

    @Column(name = "is_cash_account", nullable = false)
    private Boolean isCashAccount;

    @Column(name = "amount")
    private BigDecimal amount;

}
