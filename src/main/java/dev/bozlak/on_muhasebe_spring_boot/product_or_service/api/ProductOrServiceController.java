package dev.bozlak.on_muhasebe_spring_boot.product_or_service.api;

import dev.bozlak.core.responses.ResponseBody;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.dtos.CreateProductOrServiceRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.service.ProductOrServiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product-or-services")
@lombok.RequiredArgsConstructor
public class ProductOrServiceController {

    private final ProductOrServiceService productOrServiceService;

    @PostMapping("create-product-or-service")
    public ResponseEntity<ResponseBody> createProductOrService(
            @Valid @RequestBody CreateProductOrServiceRequestDto createProductOrServiceRequestDto,
            @RequestAttribute(name = "userId") Integer userId
    ) {
        this.productOrServiceService.createProductOrService(createProductOrServiceRequestDto, userId);
        return new ResponseEntity<>(
                new ResponseBody(true),
                HttpStatus.CREATED
        );
    }
}
