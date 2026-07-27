package dev.bozlak.on_muhasebe_spring_boot.login.api;

import dev.bozlak.core.responses.ResponseBodyWithObject;
import dev.bozlak.on_muhasebe_spring_boot.login.dtos.LoginRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.login.dtos.LoginResponseDto;
import dev.bozlak.on_muhasebe_spring_boot.login.service.LoginService;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    @SecurityRequirements     // This is the only endpoint that doesn't need a JWT token.
    public ResponseEntity<ResponseBodyWithObject<LoginResponseDto>> login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ){
        String jwtToken = this.loginService.login(loginRequestDto);
        return new ResponseEntity<>(
                new ResponseBodyWithObject<>(new LoginResponseDto(jwtToken)),
                HttpStatus.OK
        );
    }
}
