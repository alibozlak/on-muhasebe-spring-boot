package dev.bozlak.on_muhasebe_spring_boot.user.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserIdAndIsAdminModel {

    private Integer userId;
    private Boolean isAdmin;
}
