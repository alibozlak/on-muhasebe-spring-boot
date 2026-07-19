package dev.bozlak.on_muhasebe_spring_boot.logs.user_himself_activities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_himself_activities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserHimselfActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_himself_activity_id")
    private Long userHimselfActivityId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "activity_type_name", nullable = false)
    private String activityTypeName;

    @Column(name = "created_at", nullable = false)
    private java.time.LocalDate createdAt;
}
