package dev.bozlak.on_muhasebe_spring_boot.admin.repository;

import dev.bozlak.on_muhasebe_spring_boot.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAdminRepository extends JpaRepository<Admin, Short> {

    @Query("SELECT a.adminId FROM Admin a WHERE a.user.userId = :userId")
    Short getAdminIdByUserId(@Param("userId") Integer userId);

}
