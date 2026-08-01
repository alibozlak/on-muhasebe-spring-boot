package dev.bozlak.on_muhasebe_spring_boot.product_or_service;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.util.Optional;

@Table("product_or_services")
public class ProductOrService {

    @Id
    @Column("product_or_service_id")
    public Long productOrServiceId;

    @Column("user_id") // <-- DB side: Foreign Key, not here. Don't use @JoinColumn.
    public Integer userId;

    @Column("product_or_service_code")
    public Long productOrServiceCode;

    @Column("product_or_service_name")
    public String productOrServiceName;

    @Column("created_at")
    public java.time.LocalDate createdAt;

    @Column("did_delete")
    public Boolean didDelete;

    @Column("sale_unit_price")
    public BigDecimal saleUnitPrice;

    @Column("purchase_unit_price")
    public BigDecimal purchaseUnitPrice;

    @Column("inventory")
    public Long inventory;

    @Column("extra_information")
    public String extraInformation;
}
