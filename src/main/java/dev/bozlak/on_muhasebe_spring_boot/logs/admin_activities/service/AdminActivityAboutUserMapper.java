package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutUser;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityAboutUserModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * adminId and userId now map straight across: the entity holds the id columns itself
 * instead of nested Admin/User associations, so no @Mapping overrides are needed.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminActivityAboutUserMapper {

    AdminActivityAboutUser toEntityFromItsAddModel(AddAdminActivityAboutUserModel addAdminActivityAboutUserModel);
}
