package dev.bozlak.on_muhasebe_spring_boot.product_or_service.service;

import dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.services.UserActivityService;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.dtos.CreateProductOrServiceRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.repository.ProductOrServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@lombok.AllArgsConstructor
@Service
public class ProductOrServiceServiceImpl implements ProductOrServiceService {

    private final ProductOrServiceRepository productOrServiceRepository;
    private final UserActivityService userActivityService;

    @Override
    @Transactional
    public void createProductOrService(
            CreateProductOrServiceRequestDto createProductOrServiceRequestDto, Integer userId
    ) {
        Long createdProductOrServiceId =
                this.productOrServiceRepository.createProductOrService(createProductOrServiceRequestDto, userId);


        this.userActivityService.addProductOrServiceCreateOrDeleteActivity(createdProductOrServiceId);
    }
}
