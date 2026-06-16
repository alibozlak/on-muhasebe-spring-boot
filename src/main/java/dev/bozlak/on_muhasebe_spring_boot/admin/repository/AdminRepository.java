package dev.bozlak.on_muhasebe_spring_boot.admin.repository;

public interface AdminRepository {

    Short getAdminIdByUserId(Integer userId);
}
