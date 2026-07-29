package dev.bozlak.on_muhasebe_spring_boot.contact.main.repository;

import dev.bozlak.on_muhasebe_spring_boot.contact.main.dtos.CreateContactRequestDto;

public interface ContactRepository {

    Long createContact(CreateContactRequestDto createContactRequestDto, Integer userId);
}
