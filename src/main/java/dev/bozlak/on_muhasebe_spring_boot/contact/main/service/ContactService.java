package dev.bozlak.on_muhasebe_spring_boot.contact.main.service;

import dev.bozlak.on_muhasebe_spring_boot.contact.main.dtos.CreateContactRequestDto;

public interface ContactService {

    void createContact(CreateContactRequestDto createContactRequestDto, Integer userId);
}
