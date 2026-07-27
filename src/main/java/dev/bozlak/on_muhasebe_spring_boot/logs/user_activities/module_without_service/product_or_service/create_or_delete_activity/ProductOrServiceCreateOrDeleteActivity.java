package dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities.module_without_service.product_or_service.create_or_delete_activity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_or_service_create_or_delete_activities")
public class ProductOrServiceCreateOrDeleteActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_or_service_create_or_delete_activity_id")
    public Long productOrServiceCreateOrDeleteActivityId;

    @Column(name = "is_activity_create", nullable = false)
    public Boolean isActivityCreate;

    @Column(name = "product_or_service_id", nullable = false)
    public Long productOrServiceId;

    @Column(name = "created_at", nullable = false)
    public java.time.LocalDate createdLogDate;
}
