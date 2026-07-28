package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service.contact_type;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityCreateOrDeleteModel;

public interface AdminActivityContactTypeCreatedOrDeletedService {

    void add(AddAdminActivityCreateOrDeleteModel addAdminActivityCreateOrDeleteModel);
}
