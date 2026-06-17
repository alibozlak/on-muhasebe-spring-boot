package dev.bozlak.on_muhasebe_spring_boot.user.api.v1;

import dev.bozlak.core.responses.ResponseBody;
import dev.bozlak.core.responses.ResponseBodyWithMessage;
import dev.bozlak.on_muhasebe_spring_boot.user.dtos.CreateUserRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<ResponseBody> createUser(
            @Valid @RequestBody CreateUserRequestDto createUserRequestDto,
            @RequestAttribute(name = "adminId") Short adminId
    ){
        if (adminId == -1)
            return new ResponseEntity<>(
                    new ResponseBodyWithMessage(false, "You are not Admin!"),
                    HttpStatus.FORBIDDEN
            );

        this.userService.createUser(createUserRequestDto, adminId);
        return new ResponseEntity<>(
                new ResponseBody(true),
                HttpStatus.CREATED
        );
    }
}
