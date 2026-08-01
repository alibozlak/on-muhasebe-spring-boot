package dev.bozlak.on_muhasebe_spring_boot.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("admins")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Admin {

    @Id
    @Column("admin_id")
    private Short adminId;

    /**
     * FK to users.user_id (unique in DB). Was a lazy @OneToOne to User, but it only ever
     * carried the id, so it is now the plain FK column the rest of the project already uses.
     */
    @Column("user_id")
    private Integer userId;

    @Column("is_active")
    private Boolean isActive;

    /**
     * FK to admins.admin_id (self reference). Was a lazy @ManyToOne to Admin.
     */
    @Column("who_created_admin_id")
    private Short whoCreatedAdminId;
}
