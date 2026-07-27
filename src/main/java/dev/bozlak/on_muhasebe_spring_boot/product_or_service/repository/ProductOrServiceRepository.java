package dev.bozlak.on_muhasebe_spring_boot.product_or_service.repository;

import dev.bozlak.on_muhasebe_spring_boot.product_or_service.dtos.CreateProductOrServiceRequestDto;

public interface ProductOrServiceRepository {

    Long createProductOrService(
            CreateProductOrServiceRequestDto createProductOrServiceRequestDto, Integer userId
    );
}
