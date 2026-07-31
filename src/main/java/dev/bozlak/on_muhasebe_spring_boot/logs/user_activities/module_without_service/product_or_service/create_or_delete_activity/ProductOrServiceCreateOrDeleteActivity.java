package dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities.module_without_service.product_or_service.create_or_delete_activity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("product_or_service_create_or_delete_activities")
public class ProductOrServiceCreateOrDeleteActivity {

    @Id
    @Column("product_or_service_create_or_delete_activity_id")
    public Long productOrServiceCreateOrDeleteActivityId;

    @Column("is_activity_create")
    public Boolean isActivityCreate;

    @Column("product_or_service_id")
    public Long productOrServiceId;

    @Column("created_at")
    public java.time.LocalDate createdLogDate;
}
