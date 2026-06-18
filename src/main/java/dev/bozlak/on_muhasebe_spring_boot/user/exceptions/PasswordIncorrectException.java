package dev.bozlak.on_muhasebe_spring_boot.user.exceptions;

public class PasswordIncorrectException extends RuntimeException {

    public PasswordIncorrectException() {
        super("Password is incorrect!!");
    }
}
