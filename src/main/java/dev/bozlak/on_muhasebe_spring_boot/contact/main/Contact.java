package dev.bozlak.on_muhasebe_spring_boot.contact.main;

import jakarta.persistence.*;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    public Long contactId;

    @Column(name = "user_id", nullable = false)     // <----- FK
    public Integer userId;

    @Column(name = "contact_name", nullable = false, unique = true)
    public String contactName;

    @Column(name = "extra_information", nullable = true)
    public String extraInformation;

    @Column(name = "contact_type_id", nullable = false)     // <--- FK
    public Byte contactTypeId;

    @Column(name = "phone_number", nullable = true)
    public String phoneNumber;

    @Column(name = "is_active", nullable = false)
    public Boolean isActive;
}
