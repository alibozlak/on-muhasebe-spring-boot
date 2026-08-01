package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.repository;

import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.ContactType;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcContactTypeRepository extends ListCrudRepository<ContactType, Byte> {
}
