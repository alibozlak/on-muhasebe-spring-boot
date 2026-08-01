package dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities.module_without_service.product_or_service.create_or_delete_activity;

import org.springframework.stereotype.Component;

@Component
@lombok.RequiredArgsConstructor
public class ProductOrServiceCreateOrDeleteActivityRepositoryImpl implements ProductOrServiceCreateOrDeleteActivityRepository
{
    private final JdbcProductOrServiceCreateOrDeleteActivityRepository jdbcProductOrServiceCreateOrDeleteActivityRepository;

    @Override
    public void add(Long productOrServiceId) {
        ProductOrServiceCreateOrDeleteActivity productOrServiceCreateOrDeleteActivity
                = new ProductOrServiceCreateOrDeleteActivity();
        productOrServiceCreateOrDeleteActivity.isActivityCreate = true;
        productOrServiceCreateOrDeleteActivity.createdLogDate = java.time.LocalDate.now();
        productOrServiceCreateOrDeleteActivity.productOrServiceId = productOrServiceId;

        this.jdbcProductOrServiceCreateOrDeleteActivityRepository.save(productOrServiceCreateOrDeleteActivity);
    }
}
