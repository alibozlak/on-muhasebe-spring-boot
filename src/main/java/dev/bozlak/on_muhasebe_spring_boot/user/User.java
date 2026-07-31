package dev.bozlak.on_muhasebe_spring_boot.user;

//import dev.bozlak.on_muhasebe_spring_boot.admin.Admin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table("users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column("user_id")
    private Integer userId;

    @Column("username")
    private String username;

    @Column("hashed_password")
    private String hashedPassword;

    @Column("email")
    private String email;

    @Column("is_active")
    private Boolean isActive;

    @Column("is_admin")
    private Boolean isAdmin;

    @Column("created_at")
    private LocalDate createdAt;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "who_created_admin_id", nullable = false)
//    private Admin whoCreatedAdmin;
    @Column("who_created_admin_id")    // <--- For users_initial.sql
    private Short whoCreatedAdminId;

    @Column("extra_information")
    private String extraInformation;
}
