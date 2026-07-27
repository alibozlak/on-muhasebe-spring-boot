package dev.bozlak.on_muhasebe_spring_boot.product_or_service.repository;

import dev.bozlak.on_muhasebe_spring_boot.product_or_service.ProductOrService;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.dtos.CreateProductOrServiceRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ProductOrServiceRepositoryImpl implements ProductOrServiceRepository {

    private final ProductOrServiceRepositoryMapper productOrServiceRepositoryMapper;
    private final JpaProductOrServiceRepository jpaProductOrServiceRepository;

    @Override
    public Long createProductOrService(
            CreateProductOrServiceRequestDto createProductOrServiceRequestDto, Integer userId
    ) {
        ProductOrService productOrService =
                this.productOrServiceRepositoryMapper.toEntityFromItsCreateDto(createProductOrServiceRequestDto);
        productOrService.createdAt = java.time.LocalDate.now();
        productOrService.didDelete = false;
        productOrService.inventory = null;

        productOrService.productOrServiceCode = this.jpaProductOrServiceRepository
                .getLastProductOrServiceCode(userId)
                .orElse(0L);
        productOrService.productOrServiceCode ++;

        return this.jpaProductOrServiceRepository.save(productOrService).productOrServiceId;
    }
}
