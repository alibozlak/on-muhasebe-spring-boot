package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.repository;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.UserHimselfActivity;
import dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities.dtos.AddUserActivityModel;
import org.mapstruct.MappingConstants;

@org.mapstruct.Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserHimselfActivityMapper {

    UserHimselfActivity toEntityFormItsAddModel(AddUserActivityModel addUserActivityModel);
}
