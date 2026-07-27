package dev.bozlak.on_muhasebe_spring_boot.product_or_service.exceptions;

public class UnitPriceCantBeNegativeException extends RuntimeException {
    public UnitPriceCantBeNegativeException(String message) {
        super(message);
    }
}
