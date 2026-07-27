package dev.bozlak.on_muhasebe_spring_boot.product_or_service.repository;

import dev.bozlak.on_muhasebe_spring_boot.product_or_service.ProductOrService;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.dtos.CreateProductOrServiceRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductOrServiceRepositoryMapper {

    ProductOrService toEntityFromItsCreateDto(CreateProductOrServiceRequestDto createProductOrServiceRequestDto);
}
