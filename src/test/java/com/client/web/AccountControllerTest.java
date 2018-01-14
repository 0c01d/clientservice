package com.client.web;

import com.client.domain.Account;
import com.client.domain.Profile;
import com.client.repository.AccountRepository;
import com.client.service.jpa.AccountServiceImpl;
import com.client.service.jpa.ProfileServiceImpl;
import com.client.web.model.AccountRequest;
import com.client.web.model.AccountResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("AccountController Test")
class AccountControllerTest {

    private final Profile profile = new Profile()
            .setId(1)
            .setFirstname("Firstname")
            .setMiddlename("Middlename")
            .setLastname("Lastname")
            .setEmail("email@mail.domain")
            .setPhoneNumber("89654118092")
            .setGender("Man")
            .setDateOfBirth("DateOfBirth");

    private final Account account = new Account()
            .setId(1)
            .setNickname("Nickname")
            .setProfile(null)
            .setUuid(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"));

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AccountServiceImpl accountService;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @MockBean
    private AccountRepository accountRepositoryMock;

    @BeforeAll
    void setUpMockMvc(WebApplicationContext wac) {
        mockMvc = webAppContextSetup(wac).build();
    }


    @Test
    @DisplayName("Get Account")
    void getAccount() throws Exception {
        Optional<Account> accountOptional = Optional.of(account);

        when(accountRepositoryMock.findById(1)).thenReturn(accountOptional);

        MvcResult result = this.mockMvc.perform(
                get("/account/1")

        )
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        AccountResponse expectedResponse = new AccountResponse(account);
        AccountResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<AccountResponse>() {
        });

//        AccountResponse actualResponse = restTemplate.postForObject("/accounts/", accountRequest, AccountResponse.class);

        verify(accountRepositoryMock, times(1)).findById(1);
        assertEquals("Invalid user response", expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Update Account")
    void updateAccount() {

        Account accountUpdate = new Account()
                .setId(1)
                .setNickname("Nickname")
                .setUuid(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"));

        Optional<Account> accountOptional = Optional.of(account);

        when(accountRepositoryMock.findById(1)).thenReturn(accountOptional);
        when(accountRepositoryMock.save(any(Account.class))).thenReturn(accountUpdate);

        AccountRequest accountRequest = new AccountRequest()
                .setNickname("Nickname");

        AccountResponse expectedResponse = new AccountResponse(account);
        expectedResponse.setNickname("Nickname");
        AccountResponse actualResponse = restTemplate.patchForObject("/account/1", accountRequest, AccountResponse.class);

        verify(accountRepositoryMock, times(1)).findById(1);
        verify(accountRepositoryMock, times(1)).save(accountUpdate);
        assertEquals("Invalid profile response", expectedResponse, actualResponse);

    }
}
