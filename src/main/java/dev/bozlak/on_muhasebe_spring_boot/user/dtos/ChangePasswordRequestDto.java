package dev.bozlak.on_muhasebe_spring_boot.user.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChangePasswordRequestDto {

    @NotNull
    @NotBlank
    private String currentPassword;

    @NotNull
    @NotBlank
    private String newPassword;
}
