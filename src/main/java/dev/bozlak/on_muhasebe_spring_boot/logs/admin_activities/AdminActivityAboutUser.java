package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities;

import dev.bozlak.on_muhasebe_spring_boot.admin.Admin;
import dev.bozlak.on_muhasebe_spring_boot.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admin_activities_about_user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminActivityAboutUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_activity_about_user_id")
    private Long adminActivityAboutUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    @Column(name = "admin_activity_type", nullable = false)
    private String adminActivityType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "created_at", nullable = false)
    private java.time.LocalDateTime createdAt;
}