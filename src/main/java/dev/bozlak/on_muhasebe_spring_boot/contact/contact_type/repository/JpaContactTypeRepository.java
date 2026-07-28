package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.repository;

import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaContactTypeRepository extends JpaRepository<ContactType, Byte> {
}
