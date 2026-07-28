package dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.api;

import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.ContactType;
import dev.bozlak.on_muhasebe_spring_boot.contact.contact_type.repository.JpaContactTypeRepository;
import dev.bozlak.on_muhasebe_spring_boot.logs
        .admin_activities
        .repository
        .about_contact_type.JpaAdminActivityContactTypeCreatedOrDeletedRepository;
import dev.bozlak.on_muhasebe_spring_boot.util.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactTypeControllerV1IntegrationTest extends BaseIntegrationTest {

    private static final String CREATE_CONTACT_TYPE_URL = "/api/v1/contact-types/create-contact-type";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JpaContactTypeRepository jpaContactTypeRepository;

    @Autowired
    JpaAdminActivityContactTypeCreatedOrDeletedRepository jpaAdminActivityContactTypeCreatedOrDeletedRepository;

    @Test
    void createContactType_persistsContactTypeAndLog_andReturns201() throws Exception {
        final short adminId = 3;

        this.mockMvc.perform(post(CREATE_CONTACT_TYPE_URL)
                        .param("contactTypeName", "Müşteri")
                        .requestAttr("adminId", adminId)
        ).andExpect(status().isCreated());

        // assert 1: contact_types table has the new record
        assertThat(this.jpaContactTypeRepository.findAll())
                .singleElement()
                .satisfies(contactType -> {
                    assertThat(contactType.contactTypeId).isNotNull();
                    assertThat(contactType.contactTypeName).isEqualTo("Müşteri");
                });

        Byte createdContactTypeId = this.jpaContactTypeRepository.findAll().getFirst().contactTypeId;

        // assert 2: The log knows the admin and the new contact type
        assertThat(this.jpaAdminActivityContactTypeCreatedOrDeletedRepository.findAll())
                .singleElement()
                .satisfies(log -> {
                    assertThat(log.adminActivityAboutContactTypeCreatedOrDeletedId).isNotNull();
                    assertThat(log.adminId).isEqualTo(adminId);
                    assertThat(log.isActivityCreate).isTrue();
                    assertThat(log.contactTypeId).isEqualTo(createdContactTypeId);
                });
    }

    @Test
    void createContactType_whenContactTypeNameIsBlank_returns400_andPersistsNothing() throws Exception {
        this.mockMvc.perform(post(CREATE_CONTACT_TYPE_URL)
                        .param("contactTypeName", "   ")
                        .requestAttr("adminId", (short) 3)
        ).andExpect(status().isBadRequest());

        // assert: The controller stops before the service, so both tables stay empty
        assertThat(this.jpaContactTypeRepository.findAll()).isEmpty();
        assertThat(this.jpaAdminActivityContactTypeCreatedOrDeletedRepository.findAll()).isEmpty();
    }

    @Test
    void createContactType_whenAdminIdIsNegative_returns403_andPersistsNothing() throws Exception {
        this.mockMvc.perform(post(CREATE_CONTACT_TYPE_URL)
                        .param("contactTypeName", "Tedarikçi")
                        .requestAttr("adminId", (short) -1)
        ).andExpect(status().isForbidden());

        // assert: A bad admin id writes nothing to the database
        assertThat(this.jpaContactTypeRepository.findAll()).isEmpty();
        assertThat(this.jpaAdminActivityContactTypeCreatedOrDeletedRepository.findAll()).isEmpty();
    }

    /**
     * A normal user has no "adminId" request attribute, because the JWT filter
     * only sets it for an admin token.
     */
    @Test
    void createContactType_whenAdminIdIsMissing_returns500_andPersistsNothing() throws Exception {
        this.mockMvc.perform(post(CREATE_CONTACT_TYPE_URL)
                        .param("contactTypeName", "Tedarikçi")
        ).andExpect(status().isInternalServerError());

        // assert: The request never reaches the service, so both tables stay empty
        assertThat(this.jpaContactTypeRepository.findAll()).isEmpty();
        assertThat(this.jpaAdminActivityContactTypeCreatedOrDeletedRepository.findAll()).isEmpty();
    }

    @Test
    void createContactType_whenSameAdminCreatesTwoOfThem_persistsBothOfThemAndTheirLogs() throws Exception {
        final short adminId = 7;

        this.mockMvc.perform(post(CREATE_CONTACT_TYPE_URL)
                        .param("contactTypeName", "Müşteri")
                        .requestAttr("adminId", adminId)
        ).andExpect(status().isCreated());

        this.mockMvc.perform(post(CREATE_CONTACT_TYPE_URL)
                        .param("contactTypeName", "Tedarikçi")
                        .requestAttr("adminId", adminId)
        ).andExpect(status().isCreated());

        // assert 1: Both contact types are in the table and each one has its own id
        List<ContactType> contactTypes = this.jpaContactTypeRepository.findAll();
        assertThat(contactTypes)
                .hasSize(2)
                .extracting(contactType -> contactType.contactTypeId)
                .doesNotContainNull()
                .doesNotHaveDuplicates();

        assertThat(contactTypes)
                .extracting(contactType -> contactType.contactTypeName)
                .containsExactlyInAnyOrder("Müşteri", "Tedarikçi");

        // assert 2: Each contact type has one create log of the same admin
        assertThat(this.jpaAdminActivityContactTypeCreatedOrDeletedRepository.findAll())
                .hasSize(2)
                .allSatisfy(log -> {
                    assertThat(log.adminId).isEqualTo(adminId);
                    assertThat(log.isActivityCreate).isTrue();
                })
                .extracting(log -> log.contactTypeId)
                .containsExactlyInAnyOrderElementsOf(
                        contactTypes.stream().map(contactType -> contactType.contactTypeId).toList()
                );
    }
}
