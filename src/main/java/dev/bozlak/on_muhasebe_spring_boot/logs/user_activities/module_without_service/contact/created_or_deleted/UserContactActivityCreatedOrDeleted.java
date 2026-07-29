package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.contact.created_or_deleted;

import jakarta.persistence.*;

@Entity
@Table(name = "user_contact_activities_created_or_deleted")
public class UserContactActivityCreatedOrDeleted {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_contact_activity_created_or_deleted_id")
    public Long userContactActivityCreatedOrDeletedId;

    @Column(name = "contact_id", nullable = false)      // <--- FK
    public Long contactId;

    @Column(name = "is_activity_create", nullable = false)
    public Boolean isActivityCreate;

    @Column(name = "which_day", nullable = false)
    public java.time.LocalDate whichDay;
}
