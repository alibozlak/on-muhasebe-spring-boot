package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_contact_type;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutContactTypeCreatedOrDeleted;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcAdminActivityContactTypeCreatedOrDeletedRepository extends
        ListCrudRepository<AdminActivityAboutContactTypeCreatedOrDeleted, Integer>
{
}
