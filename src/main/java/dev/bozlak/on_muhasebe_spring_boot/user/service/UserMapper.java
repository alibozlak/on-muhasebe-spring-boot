package dev.bozlak.on_muhasebe_spring_boot.user.service;

import dev.bozlak.on_muhasebe_spring_boot.user.User;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.CreateUserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "hashedPassword", source = "password")
    User toEntityFromItsCreateRequestDto(CreateUserRequestDto createUserRequestDto);
}
