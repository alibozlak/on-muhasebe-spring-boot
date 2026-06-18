package dev.bozlak.on_muhasebe_spring_boot.logs.user_activities.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AddUserActivityModel {

    private Integer userId;
    private String activityTypeName;
    private java.time.LocalDate createdAt;
}
