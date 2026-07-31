package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.module_without_service.contact.created_or_deleted;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_contact_activities_created_or_deleted")
public class UserContactActivityCreatedOrDeleted {

    @Id
    @Column("user_contact_activity_created_or_deleted_id")
    public Long userContactActivityCreatedOrDeletedId;

    @Column("contact_id")      // <--- FK
    public Long contactId;

    @Column("is_activity_create")
    public Boolean isActivityCreate;

    @Column("which_day")
    public java.time.LocalDate whichDay;
}
