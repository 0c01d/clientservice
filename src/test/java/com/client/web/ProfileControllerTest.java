package com.client.web;


import com.client.domain.Profile;
import com.client.repository.ProfileRepository;
import com.client.web.model.ProfileRequest;
import com.client.web.model.ProfileResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@DisplayName("ProfileController Test")
class ProfileControllerTest {
    private final Profile profile = new Profile()
            .setId(1)
            .setFirstname("Firstname")
            .setMiddlename("Middlename")
            .setLastname("Lastname")
            .setEmail("email@mail.domain")
            .setPhone("89654118092")
            .setGender("Man")
            .setDateOfBirth("DateOfBirth");


    @Autowired
    private TestRestTemplate restTemplate;


    @MockBean
    private ProfileRepository profileRepositoryMock;


    @Test
    @DisplayName("Create_Profile")
    void createProfile() {
        Profile profileNoId = new Profile()
                .setFirstname(profile.getFirstname())
                .setMiddlename(profile.getMiddlename())
                .setLastname(profile.getLastname())
                .setEmail(profile.getEmail())
                .setPhone(profile.getPhone())
                .setGender(profile.getGender())
                .setDateOfBirth(profile.getDateOfBirth())
                .setAccount(null);
        when(profileRepositoryMock.save(profileNoId)).thenReturn(profile);

        ProfileRequest profileRequest = new ProfileRequest()
                .setFirstname(profile.getFirstname())
                .setMiddlename(profile.getMiddlename())
                .setLastname(profile.getLastname())
                .setEmail(profile.getEmail())
                .setGender(profile.getGender())
                .setPhone(profile.getPhone())
                .setDateOfBirth(profile.getDateOfBirth());

        ProfileResponse expectedResponse = new ProfileResponse(profile);

        ProfileResponse actualResponse = restTemplate.postForObject("/profile/", profileRequest, ProfileResponse.class);

        verify(profileRepositoryMock, times(1)).save(profileNoId);
        assertEquals("Invalid user response", expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Get_Profile")
    void getProfile(){
        Optional<Profile> profileOptional = Optional.of(profile);

        when(profileRepositoryMock.findById(1)).thenReturn(profileOptional);

        ProfileResponse expectedResponse = new ProfileResponse(profile);
        ProfileResponse actualResponse = restTemplate.getForObject("/profile/1", ProfileResponse.class);

        verify(profileRepositoryMock, times(1)).findById(1);
        assertEquals("Invalid car response", expectedResponse, actualResponse);
    }

    @Test
    @DisplayName("Update_Profile")
    void updateProfile(){
        Profile profileUpdate = new Profile()
                .setId(1)
                .setFirstname("Firstname")
                .setMiddlename("Middlename")
                .setLastname("Lastname")
                .setEmail("EMAIL")
                .setGender("Man")
                .setPhone("89654118092")
                .setDateOfBirth("DateOfBirth");

        Optional<Profile> profileOptional = Optional.of(profile);

        when(profileRepositoryMock.findById(1)).thenReturn(profileOptional);
        when(profileRepositoryMock.save(any(Profile.class))).thenReturn(profileUpdate);

        ProfileRequest profileRequest = new ProfileRequest()
                .setEmail("EMAIL");

        ProfileResponse expectedResponse = new ProfileResponse(profile);
        expectedResponse.setEmail("EMAIL");
        ProfileResponse actualResponse = restTemplate.patchForObject("/profile/1", profileRequest, ProfileResponse.class);

        verify(profileRepositoryMock, times(1)).findById(1);
        verify(profileRepositoryMock, times(1)).save(profileUpdate);
        assertEquals("Invalid profile response", expectedResponse, actualResponse);

    }

    @Test
    @DisplayName("Delete_profile")
    void deleteProfile() {
        restTemplate.delete("/profile/1");
        verify(profileRepositoryMock, times(1)).deleteById(1);
    }

}


