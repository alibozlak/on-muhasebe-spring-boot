package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.service.for_user_module;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.dtos.AddUserActivityModel;
import org.mapstruct.MappingConstants;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface Mapper {

    AddUserActivityModel toAddLogModuleModelFromAddUserModuleModel(
            dev.bozlak.on_muhasebe_spring_boot.user.service.logging.AddUserActivityModel addUserActivityModel
    );


}
