package dev.bozlak.on_muhasebe_spring_boot.contact.main.repository;

import dev.bozlak.on_muhasebe_spring_boot.contact.main.Contact;
import dev.bozlak.on_muhasebe_spring_boot.contact.main.dtos.CreateContactRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContactRepositoryMapper {

    Contact toEntityFromItsCreateRequestDto(CreateContactRequestDto createContactRequestDto);
}
