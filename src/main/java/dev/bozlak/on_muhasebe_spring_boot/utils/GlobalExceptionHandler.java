package dev.bozlak.on_muhasebe_spring_boot.utils;

import dev.bozlak.core.responses.ResponseBodyWithMessage;
import dev.bozlak.on_muhasebe_spring_boot.account.exceptions.CashAmountValueCantBeNegativeException;
import dev.bozlak.on_muhasebe_spring_boot.account.exceptions.UserAccountDidNotCreateException;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.exceptions.PurchaseUnitPriceCantBeNegativeException;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.exceptions.UnitPriceCantBeNegativeException;
import dev.bozlak.on_muhasebe_spring_boot.user.exceptions.PasswordIncorrectException;
import dev.bozlak.on_muhasebe_spring_boot.user.exceptions.UserIdNotFoundException;
import dev.bozlak.on_muhasebe_spring_boot.user.exceptions.UsernameNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseBodyWithMessage> handleValidationExceptions(MethodArgumentNotValidException ex){
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        log.warn("Threw validation exception(s) : {}", errorMessage);

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, errorMessage),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ResponseBodyWithMessage> handleUserNotFoundException(UsernameNotFoundException e){

        log.warn("Threw UserNotFoundException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserIdNotFoundException.class)
    public ResponseEntity<ResponseBodyWithMessage> handleUserIdNotFoundException(
            UserIdNotFoundException e
    ) {
        log.warn("Threw UserIdNotFoundException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(PasswordIncorrectException.class)
    public ResponseEntity<ResponseBodyWithMessage> handlePasswordIncorrectException(
            PasswordIncorrectException e
    ) {
        log.warn("Threw PasswordIncorrectException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, e.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ResponseBodyWithMessage> handleBadCredentialsException(BadCredentialsException e){
        log.warn("Threw BadCredentialsException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, e.getMessage()),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ResponseBodyWithMessage> handleNoResourceFoundException(NoResourceFoundException e){
        log.warn("Threw NoResourceFoundException : {} ", e.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(CashAmountValueCantBeNegativeException.class)
    public ResponseEntity<ResponseBodyWithMessage> handleCashAmountValueCantBeNegativeException(
            CashAmountValueCantBeNegativeException e
    ) {
        log.info("Threw CashAmountValueCantBeNegativeException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserAccountDidNotCreateException.class)
    public ResponseEntity<ResponseBodyWithMessage> handleUserAccountDidNotCreateException(
            UserAccountDidNotCreateException e
    ) {
        log.error("Threw UserAccountDidNotCreateException : {}", e.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(
                        false,
                        "Unexpected Backend Error!! User account didn't create!"
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(UnitPriceCantBeNegativeException.class)
    public ResponseEntity<ResponseBodyWithMessage> handlePriceCantBeNegativeException(
            UnitPriceCantBeNegativeException e
    ) {
        String messageVariable = (e instanceof PurchaseUnitPriceCantBeNegativeException) ?
                "PurchaseUnitPriceCantBeNegativeException" :
                "SaleUnitPriceCantBeNegativeException";

        log.info("Threw {} : {}", messageVariable, e.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }


    /* ----------------------------------------------------------- */
    /* ---------------- LOGGING OR OTHER EXCEPTIONS -------------- */
    /* ----------------------------------------------------------- */

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseBodyWithMessage> handleGeneralExceptions(Exception exception){

        log.error("Unexpected system error : {}", exception.getMessage());

        return new ResponseEntity<>(
                new ResponseBodyWithMessage(false, exception.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
