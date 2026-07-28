package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.api;

import dev.bozlak.core.responses.ResponseBody;
import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.exceptions.ContactTypeNameMustNotBlankException;
import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.exceptions.RequiredBeAdminForCreateContactTypeException;
import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.service.ContactTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/contact-types")
@lombok.RequiredArgsConstructor
public class ContactTypeControllerV1 {

    private final ContactTypeService contactTypeService;

    @PostMapping("/create-contact-type")
    public ResponseEntity<ResponseBody> createContactType(
            @RequestParam("contactTypeName") String contactTypeName,
            @RequestAttribute(name = "adminId") Short adminId
    ){
        if (contactTypeName.isBlank())
            throw new ContactTypeNameMustNotBlankException();
        if (adminId == null || adminId < 0)
            throw new RequiredBeAdminForCreateContactTypeException();

        this.contactTypeService.createContactType(contactTypeName, adminId);
        return new ResponseEntity<>(
                new ResponseBody(true),
                HttpStatus.CREATED
        );
    }
}
