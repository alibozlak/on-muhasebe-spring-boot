package dev.bozlak.on_muhasebe_spring_boot.product_or_service.exceptions;

public class SaleUnitPriceCantBeNegativeException extends UnitPriceCantBeNegativeException {

    public SaleUnitPriceCantBeNegativeException() {
        super("Sale Unit Price can't be a negative!!");
    }
}
