package dev.bozlak.on_muhasebe_spring_boot.account.dtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class CreateAccountRequestDtoTest {

    @Test
    void constructor_IfDtosIsCashAccountParameterTrueAndAmountFieldNegativeShouldThrowCashAmountValueCantBeNegativeException(){
        final double cashAmount = -12000.00;
       try {
           new CreateAccountRequestDto("Nakit Hesap", true, new BigDecimal(cashAmount));
       } catch (Exception e){
           String exceptionName = e.getClass().getName();
           String expected = "dev.bozlak.on_muhasebe_spring_boot.account.exceptions.CashAmountValueCantBeNegativeException";
           Assertions.assertEquals(expected, exceptionName);

           String exceptionMessage = String.format(
                   "Cash account value must not be a negative!! Your value : %.2f", cashAmount
           );
           Assertions.assertEquals(exceptionMessage, e.getMessage());
       }
    }
}
