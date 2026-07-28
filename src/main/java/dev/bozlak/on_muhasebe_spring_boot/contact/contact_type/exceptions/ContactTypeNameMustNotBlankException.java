package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.exceptions;

public class ContactTypeNameMustNotBlankException extends RuntimeException {
    public ContactTypeNameMustNotBlankException() {
      super("Contact Type name must not blank!!");
    }
}
