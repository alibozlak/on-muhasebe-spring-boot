package dev.bozlak.on_muhasebe_spring_boot.admin.repository;

import dev.bozlak.on_muhasebe_spring_boot.admin.Admin;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JdbcAdminRepository extends ListCrudRepository<Admin, Short> {

    @Query("SELECT admin_id FROM admins WHERE user_id = :userId")
    Short getAdminIdByUserId(@Param("userId") Integer userId);

}
