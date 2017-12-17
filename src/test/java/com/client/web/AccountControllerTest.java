package com.client.web;

import com.client.domain.Account;
import com.client.domain.Profile;
import com.client.repository.AccountRepository;
import com.client.service.jpa.AccountServiceImpl;
import com.client.service.jpa.ProfileServiceImpl;
import com.client.web.model.AccountRequest;
import com.client.web.model.AccountResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.Optional;
import java.util.UUID;

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
            .setPhone("89654118092")
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
    void setUpMockMvc(WebApplicationContext wac){
        mockMvc = webAppContextSetup(wac).build();
    }
    /*void profileCreate() {
             Profile profile = new Profile();
                profile.setId(1);
                profile.setFirstname("Firstname");
                profile.setMiddlename("Middlename");
                profile.setLastname("Lastname");
                profile.setEmail("email@mail.domain");
                profile.setPhone("89654118092");
                profile.setGender("Man");
                profile.setDateOfBirth("DateOfBirth");
                profile.setAccount();
    }*/

    /*@Test
    @DisplayName("Create_Account")
    void createAccount() throws Exception {
        Account accountNoId = new Account()
                .setNickname(account.getNickname())
                .setProfile(profileService.getById(1))
                .setUuid(account.getUuid());

        when(accountRepositoryMock.save(accountNoId)).thenReturn(account);

        AccountRequest accountRequest = new AccountRequest()
                .setNickname("Tim")
                .setProfileId(1)
                .setWalletUUID(UUID.fromString("23b78e32-d319-4611-afbd-7c5a1ef6c70d"));

        AccountResponse expectedResponse = new AccountResponse(account);

        MvcResult result = this.mockMvc.perform(
                post("/account/")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(accountRequest))
        )
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/account/1"))
                .andReturn();
        AccountResponse actualResponse = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<AccountResponse>() {});

//        AccountResponse actualResponse = restTemplate.postForObject("/accounts/", accountRequest, AccountResponse.class);

        verify(accountRepositoryMock, times(1)).save(accountNoId);
        assertEquals("Invalid user response", expectedResponse, actualResponse);
    }*/

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
        AccountResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<AccountResponse>() {});

//        AccountResponse actualResponse = restTemplate.postForObject("/accounts/", accountRequest, AccountResponse.class);

        verify(accountRepositoryMock, times(1)).findById(1);
        assertEquals("Invalid user response", expectedResponse, actualResponse);
    }

   /* @Test
    @DisplayName("Get Account By Name")
    void getAccountByName() throws Exception {

        MvcResult result = this.mockMvc.perform(
                get("/account/Nickname")

        )
                .andExpect(status().isOk())
                .andReturn();
        AccountResponse expectedResponse = new AccountResponse(account);
        AccountResponse actualResponse = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<AccountResponse>() {});

//        AccountResponse actualResponse = restTemplate.postForObject("/accounts/", accountRequest, AccountResponse.class);

        verify(accountRepositoryMock, times(1)).findAccountByNickname("Nickname");
        assertEquals("Invalid user response", expectedResponse, actualResponse);
    }*/

    @Test
    @DisplayName("Update Account")
    void updateAccount(){

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
