package dev.bozlak.on_muhasebe_spring_boot.contact.main.api;

import dev.bozlak.core.responses.ResponseBody;
import dev.bozlak.on_muhasebe_spring_boot.contact.main.dtos.CreateContactRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.contact.main.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contacts")
@lombok.RequiredArgsConstructor
public class ContactControllerV1 {

    private final ContactService contactService;

    @PostMapping("/create-contact")
    public ResponseEntity<ResponseBody> createContact(
            @Valid @RequestBody CreateContactRequestDto createContactRequestDto,
            @RequestAttribute(name = "userId") Integer userId
    ){
        this.contactService.createContact(createContactRequestDto, userId);

        return new ResponseEntity<>(
                new ResponseBody(true),
                HttpStatus.CREATED
        );
    }
}
