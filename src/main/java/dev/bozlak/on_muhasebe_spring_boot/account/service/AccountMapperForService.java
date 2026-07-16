package dev.bozlak.on_muhasebe_spring_boot.account.service;

import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.account.dtos.CreateAccountRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountMapperForService {

    CreateAccountRequestModel toModelFromDto(CreateAccountRequestDto createAccountRequestDto);
}
