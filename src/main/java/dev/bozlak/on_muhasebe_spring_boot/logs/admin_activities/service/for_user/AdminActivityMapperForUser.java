package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.service.for_user;

import dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos.AddAdminActivityAboutUserModel;
import dev.bozlak.on_muhasebe_spring_boot.user.service.logging.AddAdminActivityModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdminActivityMapperForUser {

    AddAdminActivityAboutUserModel toAdminActivityModuleFromUserModule(AddAdminActivityModel addAdminActivityModel);
}
