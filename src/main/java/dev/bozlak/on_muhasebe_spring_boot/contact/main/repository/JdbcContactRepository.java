package dev.bozlak.on_muhasebe_spring_boot.contact.main.repository;

import dev.bozlak.on_muhasebe_spring_boot.contact.main.Contact;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcContactRepository extends ListCrudRepository<Contact, Long> {
}
