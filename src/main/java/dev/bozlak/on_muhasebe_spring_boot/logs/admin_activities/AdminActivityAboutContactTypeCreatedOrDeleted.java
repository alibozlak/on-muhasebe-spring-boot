package dev.bozlak.on_muhasebe_spring_boot.logs.admin_activities;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_activities_about_contact_type_created_or_deleted")
public class AdminActivityAboutContactTypeCreatedOrDeleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_activity_about_contact_type_created_or_deleted_id")
    public Integer adminActivityAboutContactTypeCreatedOrDeletedId;

    /**
     * FK
     */
    @Column(name = "admin_id", nullable = false)
    public Short adminId;

    @Column(name = "is_activity_create", nullable = false)
    public Boolean isActivityCreate;

    /**
     * FK
     */
    @Column(name = "contact_type_id", nullable = false)
    public Byte contactTypeId;
}
