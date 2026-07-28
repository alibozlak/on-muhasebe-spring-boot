package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type;

import jakarta.persistence.*;

@Entity
@Table(name = "contact_types")
public class ContactType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_type_id")
    public Byte contactTypeId;

    @Column(name = "contact_type_name", nullable = false)
    public String contactTypeName;
}
