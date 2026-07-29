package dev.bozlak.on_muhasebe_spring_boot.contact.main.repository;

import dev.bozlak.on_muhasebe_spring_boot.contact.main.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaContactRepository extends JpaRepository<Contact, Long> {
}
