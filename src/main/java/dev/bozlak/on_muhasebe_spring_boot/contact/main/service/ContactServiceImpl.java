package dev.bozlak.on_muhasebe_spring_boot.contact.main.service;

import dev.bozlak.on_muhasebe_spring_boot.contact.main.dtos.CreateContactRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.contact.main.repository.ContactRepository;
import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.services.UserActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@lombok.RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final UserActivityService userActivityService;

    @Override
    @Transactional      // <--- Future maybe convert event driven or AOP
    public void createContact(CreateContactRequestDto createContactRequestDto, Integer userId) {
        Long createdContactId = this.contactRepository.createContact(createContactRequestDto, userId);

        this.userActivityService.addContactCreateOrDeleteActivity(createdContactId);
    }
}
