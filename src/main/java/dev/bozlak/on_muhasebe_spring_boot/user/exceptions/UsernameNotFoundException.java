package dev.bozlak.on_muhasebe_spring_boot.user.exceptions;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("This username not found the system : " + username);
    }
}
