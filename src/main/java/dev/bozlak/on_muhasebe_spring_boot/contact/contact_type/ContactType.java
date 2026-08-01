package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("contact_types")
public class ContactType {

    @Id
    @Column("contact_type_id")
    public Byte contactTypeId;

    @Column("contact_type_name")
    public String contactTypeName;
}
