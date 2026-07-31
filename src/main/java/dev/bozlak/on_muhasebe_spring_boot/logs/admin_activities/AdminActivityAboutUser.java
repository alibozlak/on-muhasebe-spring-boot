package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("admin_activities_about_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminActivityAboutUser {

    @Id
    @Column("admin_activity_about_user_id")
    private Long adminActivityAboutUserId;

    /**
     * FK to admins.admin_id. Was a lazy @ManyToOne to Admin; only the id was ever read.
     */
    @Column("admin_id")
    private Short adminId;

    @Column("admin_activity_type")
    private String adminActivityType;

    /**
     * FK to users.user_id. Was a lazy @ManyToOne to User; only the id was ever read.
     */
    @Column("user_id")
    private Integer userId;

    @Column("created_at")
    private java.time.LocalDateTime createdAt;
}
