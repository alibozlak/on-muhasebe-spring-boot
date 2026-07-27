package dev.bozlak.on_muhasebe_spring_boot.product_or_service.dtos;

import dev.bozlak.on_muhasebe_spring_boot.product_or_service.exceptions.PurchaseUnitPriceCantBeNegativeException;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.exceptions.SaleUnitPriceCantBeNegativeException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public final class CreateProductOrServiceRequestDto {

    @NotNull
    @NotBlank
    public final String productOrServiceName;

    public final BigDecimal saleUnitPrice;
    public final BigDecimal purchaseUnitPrice;
    public final String extraInformation;

    public CreateProductOrServiceRequestDto(
            String productOrServiceName,
            BigDecimal saleUnitPrice,
            BigDecimal purchaseUnitPrice,
            String extraInformation
    ){
        if (saleUnitPrice != null && saleUnitPrice.doubleValue() < 0)
            throw new SaleUnitPriceCantBeNegativeException();
        if (purchaseUnitPrice != null && purchaseUnitPrice.doubleValue() < 0)
            throw new PurchaseUnitPriceCantBeNegativeException();

        this.productOrServiceName = productOrServiceName;
        this.saleUnitPrice = saleUnitPrice;
        this.purchaseUnitPrice = purchaseUnitPrice;
        this.extraInformation = extraInformation;
    }
}
