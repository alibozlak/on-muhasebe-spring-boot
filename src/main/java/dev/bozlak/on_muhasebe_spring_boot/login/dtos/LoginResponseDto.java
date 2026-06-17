package dev.bozlak.on_muhasebe_spring_boot.login.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponseDto {

    private String jwtToken;
}
