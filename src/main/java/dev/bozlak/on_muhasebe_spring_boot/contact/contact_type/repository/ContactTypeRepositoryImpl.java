package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.repository;

import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.ContactType;
import org.springframework.stereotype.Component;

@lombok.RequiredArgsConstructor
@Component
public class ContactTypeRepositoryImpl implements ContactTypeRepository {

    private final JpaContactTypeRepository jpaContactTypeRepository;

    @Override
    public Byte createContactType(String contactTypeName) {
        ContactType contactType = new ContactType();
        contactType.contactTypeName = contactTypeName;

        return this.jpaContactTypeRepository.save(contactType).contactTypeId;
    }
}
