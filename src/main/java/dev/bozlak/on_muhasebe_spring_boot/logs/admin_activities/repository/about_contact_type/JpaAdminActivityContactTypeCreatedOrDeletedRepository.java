package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_contact_type;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutContactTypeCreatedOrDeleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAdminActivityContactTypeCreatedOrDeletedRepository extends
        JpaRepository<AdminActivityAboutContactTypeCreatedOrDeleted, Integer>
{
}
