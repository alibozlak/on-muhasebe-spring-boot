package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutUser;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityAboutUserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminActivityMapper {

    @Mapping(target = "admin.adminId", source = "adminId")
    @Mapping(target = "user.userId", source = "userId")
    AdminActivityAboutUser toEntityFromItsAddModel(AddAdminActivityAboutUserModel addAdminActivityAboutUserModel);
}
