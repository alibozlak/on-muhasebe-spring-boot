package dev.bozlak.on_muhasebe_spring_boot.contact.main;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("contacts")
public class Contact {

    @Id
    @Column("contact_id")
    public Long contactId;

    @Column("user_id")     // <----- FK
    public Integer userId;

    @Column("contact_name")
    public String contactName;

    @Column("extra_information")
    public String extraInformation;

    @Column("contact_type_id")     // <--- FK
    public Byte contactTypeId;

    @Column("phone_number")
    public String phoneNumber;

    @Column("is_active")
    public Boolean isActive;
}
