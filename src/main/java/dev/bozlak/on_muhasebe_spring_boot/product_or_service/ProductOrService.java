package dev.bozlak.on_muhasebe_spring_boot.product_or_service;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Optional;

@Entity
@Table(name = "product_or_services")
public class ProductOrService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_or_service_id")
    public Long productOrServiceId;

    @Column(name = "user_id", nullable = false) // <-- DB side: Foreign Key, not here. Don't use @JoinColumn.
    public Integer userId;

    @Column(name = "product_or_service_code", nullable = false)
    public Long productOrServiceCode;

    @Column(name = "product_or_service_name", nullable = false)
    public String productOrServiceName;

    @Column(name = "created_at", nullable = false)
    public java.time.LocalDate createdAt;

    @Column(name = "did_delete", nullable = false)
    public Boolean didDelete;

    @Column(name = "sale_unit_price")
    public BigDecimal saleUnitPrice;

    @Column(name = "purchase_unit_price")
    public BigDecimal purchaseUnitPrice;

    @Column(name = "inventory")
    public Long inventory;

    @Column(name = "extra_information")
    public String extraInformation;
}
