package dev.bozlak.on_muhasebe_spring_boot.user.service.logging;

public record AddAdminActivityModel(
        Short adminId,
        String adminActivityType,
        Integer userId
) {
}
