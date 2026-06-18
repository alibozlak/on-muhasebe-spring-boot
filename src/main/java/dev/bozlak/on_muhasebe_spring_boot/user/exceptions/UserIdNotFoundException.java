package dev.bozlak.on_muhasebe_spring_boot.user.exceptions;

public class UserIdNotFoundException extends RuntimeException {

    public UserIdNotFoundException(Integer userId) {
      super("This userId not found : " + userId);
    }
}
