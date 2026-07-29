package dev.bozlak.on_muhasebe_spring_boot.contact.main.repository;

import dev.bozlak.on_muhasebe_spring_boot.contact.main.Contact;
import dev.bozlak.on_muhasebe_spring_boot.contact.main.dtos.CreateContactRequestDto;
import org.springframework.stereotype.Repository;

@Repository
@lombok.RequiredArgsConstructor
public class ContactRepositoryImpl implements ContactRepository {

    private final JpaContactRepository jpaContactRepository;
    private final ContactRepositoryMapper contactRepositoryMapper;

    @Override
    public Long createContact(CreateContactRequestDto createContactRequestDto, Integer userId) {
        Contact contact = this.contactRepositoryMapper.toEntityFromItsCreateRequestDto(createContactRequestDto);
        contact.userId = userId;
        contact.isActive = true;

        return this.jpaContactRepository.save(contact).contactId;
    }
}
