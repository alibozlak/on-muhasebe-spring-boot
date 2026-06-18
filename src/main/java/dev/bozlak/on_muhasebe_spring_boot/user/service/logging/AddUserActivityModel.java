package dev.bozlak.on_muhasebe_spring_boot.user.service.logging;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddUserActivityModel {

    private Integer userId;
    private String activityTypeName;
}
