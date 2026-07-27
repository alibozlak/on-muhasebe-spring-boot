package dev.bozlak.on_muhasebe_spring_boot.product_or_service.repository;

import dev.bozlak.on_muhasebe_spring_boot.product_or_service.ProductOrService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaProductOrServiceRepository extends JpaRepository<ProductOrService, Long> {

    @Query("SELECT p.productOrServiceCode FROM ProductOrService p " +
            "WHERE p.userId  = :userId ORDER BY p.productOrServiceCode DESC LIMIT 1")
    Optional<Long> getLastProductOrServiceCode(@Param("userId") Integer userId);
}
