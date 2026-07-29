package dev.bozlak.on_muhasebe_spring_boot.contact.main.api;

import dev.bozlak.on_muhasebe_spring_boot.contact.main.Contact;
import dev.bozlak.on_muhasebe_spring_boot.contact.main.dtos.CreateContactRequestDto;
import dev.bozlak.on_muhasebe_spring_boot.contact.main.repository.JpaContactRepository;
import dev.bozlak.on_muhasebe_spring_boot.logs
        .user_activities
        .module_without_service
        .contact
        .created_or_deleted.JpaUserContactActivityCreatedOrDeletedRepository;
import dev.bozlak.on_muhasebe_spring_boot.util.BaseIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ContactControllerV1IntegrationTest extends BaseIntegrationTest {

    private static final String CREATE_CONTACT_URL = "/api/v1/contacts/create-contact";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    JpaContactRepository jpaContactRepository;

    @Autowired
    JpaUserContactActivityCreatedOrDeletedRepository jpaUserContactActivityCreatedOrDeletedRepository;

    @Test
    void createContact_persistsContactAndLog_andReturns201() throws Exception {
        final int userId = 12;
        CreateContactRequestDto createContactRequestDto = new CreateContactRequestDto(
                "Ahmet Yılmaz",
                "Uzun yıllardır çalıştığımız müşteri",
                (byte) 1,
                "05551112233"
        );

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createContactRequestDto))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        // assert 1: contacts table has the new record
        assertThat(this.jpaContactRepository.findAll())
                .singleElement()
                .satisfies(contact -> {
                    assertThat(contact.contactId).isNotNull();
                    assertThat(contact.userId).isEqualTo(userId);
                    assertThat(contact.contactName).isEqualTo("Ahmet Yılmaz");
                    assertThat(contact.extraInformation).isEqualTo("Uzun yıllardır çalıştığımız müşteri");
                    assertThat(contact.contactTypeId).isEqualTo((byte) 1);
                    assertThat(contact.phoneNumber).isEqualTo("05551112233");
                    assertThat(contact.isActive).isTrue();
                });

        Long createdContactId = this.jpaContactRepository.findAll().getFirst().contactId;

        // assert 2: The log knows the new contact and marks the activity as a create
        assertThat(this.jpaUserContactActivityCreatedOrDeletedRepository.findAll())
                .singleElement()
                .satisfies(log -> {
                    assertThat(log.userContactActivityCreatedOrDeletedId).isNotNull();
                    assertThat(log.contactId).isEqualTo(createdContactId);
                    assertThat(log.isActivityCreate).isTrue();
                    assertThat(log.whichDay).isBeforeOrEqualTo(LocalDate.now());
                });
    }

    @Test
    void createContact_whenOptionalFieldsAreNull_persistsThemAsNull_andReturns201() throws Exception {
        final int userId = 34;
        CreateContactRequestDto createContactRequestDto = new CreateContactRequestDto(
                "Bozlak Yazılım Ltd. Şti.", null, (byte) 2, null
        );

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createContactRequestDto))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        // assert 1: Only the mandatory columns are filled, the optional ones stayed null
        assertThat(this.jpaContactRepository.findAll())
                .singleElement()
                .satisfies(contact -> {
                    assertThat(contact.userId).isEqualTo(userId);
                    assertThat(contact.contactName).isEqualTo("Bozlak Yazılım Ltd. Şti.");
                    assertThat(contact.contactTypeId).isEqualTo((byte) 2);
                    assertThat(contact.extraInformation).isNull();
                    assertThat(contact.phoneNumber).isNull();
                    assertThat(contact.isActive).isTrue();
                });

        // assert 2: The create log is written for the optional-free request too
        assertThat(this.jpaUserContactActivityCreatedOrDeletedRepository.findAll()).hasSize(1);
    }

    @Test
    void createContact_whenContactNameIsBlank_returns400_andPersistsNothing() throws Exception {
        CreateContactRequestDto createContactRequestDto = new CreateContactRequestDto(
                "   ", "Adı boş bırakılmış cari", (byte) 1, "05551112233"
        );

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createContactRequestDto))
                        .requestAttr("userId", 12)
        ).andExpect(status().isBadRequest());

        // assert: The validation stops before the service, so both tables stay empty
        assertThat(this.jpaContactRepository.findAll()).isEmpty();
        assertThat(this.jpaUserContactActivityCreatedOrDeletedRepository.findAll()).isEmpty();
    }

    @Test
    void createContact_whenContactTypeIdIsNull_returns400_andPersistsNothing() throws Exception {
        CreateContactRequestDto createContactRequestDto = new CreateContactRequestDto(
                "Ahmet Yılmaz", null, null, "05551112233"
        );

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createContactRequestDto))
                        .requestAttr("userId", 12)
        ).andExpect(status().isBadRequest());

        // assert: Neither the contact nor its log was written
        assertThat(this.jpaContactRepository.findAll()).isEmpty();
        assertThat(this.jpaUserContactActivityCreatedOrDeletedRepository.findAll()).isEmpty();
    }

    /**
     * The contact type ids start from 1, so a 0 breaks the {@code @Min(1)} rule of the request dto.
     */
    @Test
    void createContact_whenContactTypeIdIsBelowOne_returns400_andPersistsNothing() throws Exception {
        CreateContactRequestDto createContactRequestDto = new CreateContactRequestDto(
                "Ahmet Yılmaz", null, (byte) 0, "05551112233"
        );

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createContactRequestDto))
                        .requestAttr("userId", 12)
        ).andExpect(status().isBadRequest());

        // assert: Neither the contact nor its log was written
        assertThat(this.jpaContactRepository.findAll()).isEmpty();
        assertThat(this.jpaUserContactActivityCreatedOrDeletedRepository.findAll()).isEmpty();
    }

    /**
     * The "userId" request attribute is set by the JWT filter, so an unauthenticated
     * request reaches the controller without it.
     */
    @Test
    void createContact_whenUserIdIsMissing_returns500_andPersistsNothing() throws Exception {
        CreateContactRequestDto createContactRequestDto = new CreateContactRequestDto(
                "Ahmet Yılmaz", null, (byte) 1, "05551112233"
        );

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(createContactRequestDto))
        ).andExpect(status().isInternalServerError());

        // assert: The request never reaches the service, so both tables stay empty
        assertThat(this.jpaContactRepository.findAll()).isEmpty();
        assertThat(this.jpaUserContactActivityCreatedOrDeletedRepository.findAll()).isEmpty();
    }

    @Test
    void createContact_whenSameUserCreatesTwoOfThem_persistsBothOfThemAndTheirLogs() throws Exception {
        final int userId = 56;

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(new CreateContactRequestDto(
                                "Ahmet Yılmaz", null, (byte) 1, "05551112233"
                        )))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(new CreateContactRequestDto(
                                "Mehmet Demir", null, (byte) 2, "05553334455"
                        )))
                        .requestAttr("userId", userId)
        ).andExpect(status().isCreated());

        // assert 1: Both of them belong to the same user and both got their own id
        List<Contact> contacts = this.jpaContactRepository.findAll();
        assertThat(contacts)
                .hasSize(2)
                .allSatisfy(contact -> {
                    assertThat(contact.userId).isEqualTo(userId);
                    assertThat(contact.isActive).isTrue();
                })
                .extracting(contact -> contact.contactId)
                .doesNotContainNull()
                .doesNotHaveDuplicates();

        assertThat(contacts)
                .extracting(contact -> contact.contactName)
                .containsExactlyInAnyOrder("Ahmet Yılmaz", "Mehmet Demir");

        // assert 2: Each contact has exactly one create log
        assertThat(this.jpaUserContactActivityCreatedOrDeletedRepository.findAll())
                .hasSize(2)
                .allSatisfy(log -> assertThat(log.isActivityCreate).isTrue())
                .extracting(log -> log.contactId)
                .containsExactlyInAnyOrderElementsOf(
                        contacts.stream().map(contact -> contact.contactId).toList()
                );
    }

    /**
     * The contact name column is unique for the whole table, so even another user
     * can not create a second contact with an already used name.
     */
    @Test
    void createContact_whenContactNameIsAlreadyUsed_returns500_andPersistsOnlyTheFirstOne() throws Exception {
        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(new CreateContactRequestDto(
                                "Ahmet Yılmaz", null, (byte) 1, "05551112233"
                        )))
                        .requestAttr("userId", 78)
        ).andExpect(status().isCreated());

        this.mockMvc.perform(post(CREATE_CONTACT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(new CreateContactRequestDto(
                                "Ahmet Yılmaz", null, (byte) 2, "05553334455"
                        )))
                        .requestAttr("userId", 90)
        ).andExpect(status().isInternalServerError());

        // assert 1: The second request left nothing behind in the contacts table
        assertThat(this.jpaContactRepository.findAll())
                .singleElement()
                .satisfies(contact -> {
                    assertThat(contact.userId).isEqualTo(78);
                    assertThat(contact.contactTypeId).isEqualTo((byte) 1);
                    assertThat(contact.phoneNumber).isEqualTo("05551112233");
                });

        // assert 2: The failed request rolled back, so it has no log either
        assertThat(this.jpaUserContactActivityCreatedOrDeletedRepository.findAll()).hasSize(1);
    }
}
