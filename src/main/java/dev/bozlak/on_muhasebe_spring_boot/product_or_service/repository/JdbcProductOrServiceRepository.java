package dev.bozlak.on_muhasebe_spring_boot.product_or_service.repository;

import dev.bozlak.on_muhasebe_spring_boot.product_or_service.ProductOrService;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JdbcProductOrServiceRepository extends ListCrudRepository<ProductOrService, Long> {

    @Query("SELECT product_or_service_code FROM product_or_services " +
            "WHERE user_id = :userId ORDER BY product_or_service_code DESC LIMIT 1")
    Optional<Long> getLastProductOrServiceCode(@Param("userId") Integer userId);
}
