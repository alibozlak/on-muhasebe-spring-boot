package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.service;

import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.repository.ContactTypeRepository;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityCreateOrDeleteModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service.contact_type.AdminActivityContactTypeCreatedOrDeletedService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@lombok.RequiredArgsConstructor
public class ContactTypeServiceImpl implements ContactTypeService {

    private final ContactTypeRepository contactTypeRepository;
    private final AdminActivityContactTypeCreatedOrDeletedService adminActivityContactTypeCreatedOrDeletedService;

    @Override
    @Transactional  // <----- Future maybe Event Driven or AOP
    public void createContactType(String contactTypeName, Short adminId) {
        Byte createdContactTypeId = this.contactTypeRepository.createContactType(contactTypeName);

        AddAdminActivityCreateOrDeleteModel addAdminActivityCreateOrDeleteModel
                = new AddAdminActivityCreateOrDeleteModel(adminId, createdContactTypeId);
        this.adminActivityContactTypeCreatedOrDeletedService.add(addAdminActivityCreateOrDeleteModel);
    }
}
