package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("admin_activities_about_contact_type_created_or_deleted")
public class AdminActivityAboutContactTypeCreatedOrDeleted {

    @Id
    @Column("admin_activity_about_contact_type_created_or_deleted_id")
    public Integer adminActivityAboutContactTypeCreatedOrDeletedId;

    /**
     * FK
     */
    @Column("admin_id")
    public Short adminId;

    @Column("is_activity_create")
    public Boolean isActivityCreate;

    /**
     * FK
     */
    @Column("contact_type_id")
    public Byte contactTypeId;
}
