package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service.contact_type;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityCreateOrDeleteModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_contact_type.AdminActivityAboutContactTypeCreatedOrDeletedRepository;
import org.springframework.stereotype.Component;

@Component
@lombok.RequiredArgsConstructor
public class AdminActivityContactTypeCreatedOrDeletedServiceImpl implements
        AdminActivityContactTypeCreatedOrDeletedService
{
    private final AdminActivityAboutContactTypeCreatedOrDeletedRepository
            adminActivityAboutContactTypeCreatedOrDeletedRepository;


    @Override
    public void add(AddAdminActivityCreateOrDeleteModel addAdminActivityCreateOrDeleteModel) {
        this.adminActivityAboutContactTypeCreatedOrDeletedRepository.add(addAdminActivityCreateOrDeleteModel);
    }
}
