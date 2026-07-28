package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.repository.about_contact_type;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityCreateOrDeleteModel;

public interface AdminActivityAboutContactTypeCreatedOrDeletedRepository {

    void add(AddAdminActivityCreateOrDeleteModel addAdminActivityCreateOrDeleteModel);
}
