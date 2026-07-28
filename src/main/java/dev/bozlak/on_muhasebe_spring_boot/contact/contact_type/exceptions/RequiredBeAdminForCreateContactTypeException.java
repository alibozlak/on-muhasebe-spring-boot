package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.exceptions;

public class RequiredBeAdminForCreateContactTypeException extends RuntimeException {
    public RequiredBeAdminForCreateContactTypeException() {
        super("Required be admin for creating contact type !!");
    }
}
