package dev.bozlak.on_muhasebe_spring_boot.account.repository;

import dev.bozlak.on_muhasebe_spring_boot.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAccountRepository extends JpaRepository<Account, Long> {
}
