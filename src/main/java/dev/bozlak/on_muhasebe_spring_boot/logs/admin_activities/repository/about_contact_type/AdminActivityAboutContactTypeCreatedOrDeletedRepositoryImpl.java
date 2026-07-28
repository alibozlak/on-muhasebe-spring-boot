package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_contact_type;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutContactTypeCreatedOrDeleted;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityCreateOrDeleteModel;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.mappers.AdminActivityMapper;
import org.springframework.stereotype.Component;

@lombok.RequiredArgsConstructor
@Component
public class AdminActivityAboutContactTypeCreatedOrDeletedRepositoryImpl
    implements AdminActivityAboutContactTypeCreatedOrDeletedRepository
{
    private final JpaAdminActivityContactTypeCreatedOrDeletedRepository
            jpaAdminActivityContactTypeCreatedOrDeletedRepository;

    private final AdminActivityMapper adminActivityMapper;

    @Override
    public void add(AddAdminActivityCreateOrDeleteModel addAdminActivityCreateOrDeleteModel) {
        AdminActivityAboutContactTypeCreatedOrDeleted adminActivityAboutContactTypeCreatedOrDeleted
                = this.adminActivityMapper.toEntityFromItsModel(addAdminActivityCreateOrDeleteModel);
        adminActivityAboutContactTypeCreatedOrDeleted.isActivityCreate = true;

        this.jpaAdminActivityContactTypeCreatedOrDeletedRepository.save(adminActivityAboutContactTypeCreatedOrDeleted);
    }
}
