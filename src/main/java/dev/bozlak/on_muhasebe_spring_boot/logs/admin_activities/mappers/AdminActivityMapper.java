package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.mappers;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.AdminActivityAboutContactTypeCreatedOrDeleted;
import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityCreateOrDeleteModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminActivityMapper {

    AdminActivityAboutContactTypeCreatedOrDeleted toEntityFromItsModel(
            AddAdminActivityCreateOrDeleteModel addAdminActivityCreateOrDeleteModel
    );

    // Other Admin Mapping methods
}
