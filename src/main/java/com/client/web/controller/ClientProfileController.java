package com.client.web.controller;

import com.client.domain.Profile;
import com.client.service.ProfileService;
import com.client.web.model.ProfileRequest;
import com.client.web.model.ProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/profile")
public class ClientProfileController {

    private final ProfileService profileService;

    @Autowired
    public ClientProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest profileRequest, HttpServletResponse response) {
        Profile profile = profileService.save(profileRequest);
        response.addHeader(HttpHeaders.LOCATION, "/profile/" + profile.getId());
        return new ProfileResponse(profile);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ProfileResponse getProfileById(@PathVariable("id") final Long id) {
        return new ProfileResponse(profileService.getById(id));
    }

    @RequestMapping(value = "/by", method = RequestMethod.GET)
    public ProfileResponse getProfileByParam(@RequestParam(value = "nickname", required = false) final String nickname,
                                             @RequestParam(value = "email", required = false) final String email,
                                             HttpServletResponse servletResponse) {
        Profile profile;
        if (nickname != null && nickname.length() > 0) {
            profile = profileService.getByUsername(nickname);
        } else if (email != null && email.length() > 0) {
            profile = profileService.getByEmail(email);
        } else {
            servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }

        if (profile != null) {
            return new ProfileResponse(profile);
        } else {
            servletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return null;
        }
    }


    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{profileId}", method = RequestMethod.PATCH)
    public ProfileResponse updateProfile(@PathVariable Long profileId,@Valid @RequestBody ProfileRequest profileRequest) {
        return new ProfileResponse(profileService.update(profileId, profileRequest));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{profileId}", method = RequestMethod.DELETE)
    public void deleteProfile(@PathVariable Long profileId) {
        profileService.deleteProfile(profileId);
    }

      /*  @RequestMapping(method = RequestMethod.GET)
    public List<ProfileResponse> getProfiles(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size) {
        Page<Profile> profilePage = profileService.getProfiles(page, size);
        return profilePage
                .stream()
                .map(ProfileResponse::new)
                .collect(Collectors.toList());
    }*/

}