package dev.bozlak.on_muhasebe_spring_boot.account.repository;

import dev.bozlak.on_muhasebe_spring_boot.account.Account;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcAccountRepository extends ListCrudRepository<Account, Long> {
}
