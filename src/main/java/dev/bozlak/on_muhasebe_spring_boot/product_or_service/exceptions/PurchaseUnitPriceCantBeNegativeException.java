package dev.bozlak.on_muhasebe_spring_boot.product_or_service.exceptions;

public class PurchaseUnitPriceCantBeNegativeException extends UnitPriceCantBeNegativeException {
    public PurchaseUnitPriceCantBeNegativeException() {
        super("Purchase unit price can't be negative!!");
    }
}
