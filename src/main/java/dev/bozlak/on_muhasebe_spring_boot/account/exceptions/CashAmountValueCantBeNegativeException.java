package dev.bozlak.on_muhasebe_spring_boot.account.exceptions;

import java.math.BigDecimal;

public class CashAmountValueCantBeNegativeException extends RuntimeException {

    public CashAmountValueCantBeNegativeException(BigDecimal negativeAmount) {
        super(
                String.format(
                        "Cash account value must not be a negative!! Your value : %.2f",
                        negativeAmount.doubleValue()
                )
        );
    }
}
