package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table("user_himself_activities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserHimselfActivity {

    @Id
    @Column("user_himself_activity_id")
    private Long userHimselfActivityId;

    @Column("user_id")
    private Integer userId;

    @Column("activity_type_name")
    private String activityTypeName;

    @Column("created_at")
    private java.time.LocalDate createdAt;
}
