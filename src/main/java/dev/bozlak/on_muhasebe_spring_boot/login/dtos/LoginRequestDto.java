package dev.bozlak.on_muhasebe_spring_boot.login.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginRequestDto {

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String password;
}
