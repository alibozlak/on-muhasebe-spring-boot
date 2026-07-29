package dev.bozlak.on_muhasebe_spring_boot.contact.main.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@lombok.AllArgsConstructor
public final class CreateContactRequestDto {

    @NotBlank
    public final String contactName;

    public final String extraInformation;

    @NotNull
    @Min(1)
    @Max(127)
    public final Byte contactTypeId;

    public final String phoneNumber;
}
