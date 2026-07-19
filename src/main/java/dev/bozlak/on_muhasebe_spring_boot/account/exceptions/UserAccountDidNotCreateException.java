package dev.bozlak.on_muhasebe_spring_boot.account.exceptions;

public class UserAccountDidNotCreateException extends RuntimeException {
    public UserAccountDidNotCreateException(String message) {
        super(message);
    }
}
