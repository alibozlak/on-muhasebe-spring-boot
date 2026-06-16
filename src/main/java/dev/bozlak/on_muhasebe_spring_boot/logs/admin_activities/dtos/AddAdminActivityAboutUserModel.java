package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities.dtos;

public record AddAdminActivityAboutUserModel(
        Short adminId,
        String adminActivityType,
        Integer userId
) {
}
