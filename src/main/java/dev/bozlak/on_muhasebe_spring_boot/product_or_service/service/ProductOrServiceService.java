package dev.bozlak.on_muhasebe_spring_boot.product_or_service.service;

import dev.bozlak.on_muhasebe_spring_boot.product_or_service.dtos.CreateProductOrServiceRequestDto;

public interface ProductOrServiceService {

    void createProductOrService(
            CreateProductOrServiceRequestDto createProductOrServiceRequestDto, Integer userId
    );
}
