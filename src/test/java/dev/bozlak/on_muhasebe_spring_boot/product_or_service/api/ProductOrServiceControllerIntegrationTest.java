package dev.bozlak.on_muhasebe_spring_boot.product_or_service.api;

import dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .product_or_service
        .create_or_delete_activity.JpaProductOrServiceCreateOrDeleteActivityRepository;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.ProductOrService;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.dtos.CreateProductOrServiceRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.product_or_service.repository.JpaProductOrServiceRepository;
import dev.bozlak.on_muhasebe_spring_boot.util.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductOrServiceControllerIntegrationTest extends BaseIntegrationTest {

    private static final String CREATE_PRODUCT_OR_SERVICE_URL =
            "/api/v1/product-or-services/create-product-or-service";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JpaProductOrServiceRepository jpaProductOrServiceRepository;

    @Autowired
    JpaProductOrServiceCreateOrDeleteActivityRepository jpaProductOrServiceCreateOrDeleteActivityRepository;

    @Test
    void createProductOrService_persistsProductOrServiceAndLog_andReturns201() throws Exception {
        final int userId = 12;
        CreateProductOrServiceRequestDto createProductOrServiceRequestDto = new CreateProductOrServiceRequestDto(
                "Mekanik klavye",
                new BigDecimal("1500.50"),
                new BigDecimal("1200.25"),
                "Türkçe Q klavye"
        );

        this.mockMvc.perform(post(CREATE_PRODUCT_OR_SERVICE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createProductOrServiceRequestDto))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        // assert 1: product_or_services table insert record
        assertThat(this.jpaProductOrServiceRepository.findAll())
                .singleElement()
                .satisfies(productOrService -> {
                    assertThat(productOrService.productOrServiceId).isNotNull();
                    assertThat(productOrService.userId).isEqualTo(userId);
                    assertThat(productOrService.productOrServiceName).isEqualTo("Mekanik klavye");
                    assertThat(productOrService.saleUnitPrice).isEqualByComparingTo("1500.50");
                    assertThat(productOrService.purchaseUnitPrice).isEqualByComparingTo("1200.25");
                    assertThat(productOrService.extraInformation).isEqualTo("Türkçe Q klavye");
                    assertThat(productOrService.productOrServiceCode).isEqualTo(1L);
                    assertThat(productOrService.didDelete).isFalse();
                    assertThat(productOrService.inventory).isNull();
                    assertThat(productOrService.createdAt).isBeforeOrEqualTo(LocalDate.now());
                });

        Long createdProductOrServiceId = this.jpaProductOrServiceRepository
                .findAll().getFirst().productOrServiceId;

        // assert 2: Did inserted record product_or_service_create_or_delete_activities table
        assertThat(this.jpaProductOrServiceCreateOrDeleteActivityRepository.findAll())
                .singleElement()
                .satisfies(log -> {
                    assertThat(log.productOrServiceCreateOrDeleteActivityId).isNotNull();
                    assertThat(log.isActivityCreate).isTrue();
                    assertThat(log.productOrServiceId).isEqualTo(createdProductOrServiceId);
                    assertThat(log.createdLogDate).isBeforeOrEqualTo(LocalDate.now());
                });
    }

    @Test
    void createProductOrService_whenOptionalFieldsAreNull_persistsThemAsNull_andReturns201() throws Exception {
        final int userId = 34;
        CreateProductOrServiceRequestDto createProductOrServiceRequestDto = new CreateProductOrServiceRequestDto(
                "Muhasebe danışmanlığı hizmeti", null, null, null
        );

        this.mockMvc.perform(post(CREATE_PRODUCT_OR_SERVICE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createProductOrServiceRequestDto))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        // assert 1: Only the mandatory columns are filled, the optional ones stayed null
        assertThat(this.jpaProductOrServiceRepository.findAll())
                .singleElement()
                .satisfies(productOrService -> {
                    assertThat(productOrService.userId).isEqualTo(userId);
                    assertThat(productOrService.productOrServiceName).isEqualTo("Muhasebe danışmanlığı hizmeti");
                    assertThat(productOrService.saleUnitPrice).isNull();
                    assertThat(productOrService.purchaseUnitPrice).isNull();
                    assertThat(productOrService.extraInformation).isNull();
                    assertThat(productOrService.didDelete).isFalse();
                });

        // assert 2: The create log is written for the optional-free request too
        assertThat(this.jpaProductOrServiceCreateOrDeleteActivityRepository.findAll()).hasSize(1);
    }

    @Test
    void createProductOrService_whenProductOrServiceNameIsBlank_returns400_andPersistsNothing() throws Exception {
        CreateProductOrServiceRequestDto createProductOrServiceRequestDto = new CreateProductOrServiceRequestDto(
                "   ", new BigDecimal("100"), new BigDecimal("80"), null
        );

        this.mockMvc.perform(post(CREATE_PRODUCT_OR_SERVICE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createProductOrServiceRequestDto))
                        .requestAttr("userId", 12)
        ).andExpect(status().isBadRequest());

        // assert: Neither the product_or_service nor its log was written
        assertThat(this.jpaProductOrServiceRepository.findAll()).isEmpty();
        assertThat(this.jpaProductOrServiceCreateOrDeleteActivityRepository.findAll()).isEmpty();
    }

    @Test
    void createProductOrService_whenSameUserCreatesTwoOfThem_persistsBothOfThemAndTheirLogs() throws Exception {
        final int userId = 56;

        this.mockMvc.perform(post(CREATE_PRODUCT_OR_SERVICE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(new CreateProductOrServiceRequestDto(
                                "Monitör", new BigDecimal("7500"), new BigDecimal("6000"), null
                        )))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        this.mockMvc.perform(post(CREATE_PRODUCT_OR_SERVICE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(new CreateProductOrServiceRequestDto(
                                "Kablosuz mouse", new BigDecimal("950"), new BigDecimal("700"), null
                        )))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        // assert 1: Both of them belong to the same user and both got their own id
        List<ProductOrService> productOrServices = this.jpaProductOrServiceRepository.findAll();
        assertThat(productOrServices)
                .hasSize(2)
                .allSatisfy(productOrService -> {
                    assertThat(productOrService.userId).isEqualTo(userId);
                    assertThat(productOrService.didDelete).isFalse();
                })
                .extracting(productOrService -> productOrService.productOrServiceName)
                .containsExactlyInAnyOrder("Monitör", "Kablosuz mouse");

        // assert 2: The same user's product or service codes go one by one
        assertThat(productOrServices)
                .extracting(productOrService -> productOrService.productOrServiceCode)
                .containsExactlyInAnyOrder(1L, 2L);

        // assert 3: Each of them has exactly one create log
        assertThat(this.jpaProductOrServiceCreateOrDeleteActivityRepository.findAll())
                .hasSize(2)
                .allSatisfy(log -> assertThat(log.isActivityCreate).isTrue())
                .extracting(log -> log.productOrServiceId)
                .containsExactlyInAnyOrderElementsOf(
                        productOrServices.stream().map(productOrService -> productOrService.productOrServiceId).toList()
                );
    }
}
