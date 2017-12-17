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
    public ProfileResponse createProfile(@RequestBody ProfileRequest profileRequest, HttpServletResponse response) {
        Profile profile = profileService.save(profileRequest);
        response.addHeader(HttpHeaders.LOCATION, "/profile/" + profile.getId());
        return new ProfileResponse(profile);
    }

    @RequestMapping(value = "/{profileId}", method = RequestMethod.GET)
    public ProfileResponse getProfileById(@PathVariable("profileId") final Integer profileId) {
        return new ProfileResponse(profileService.getById(profileId));
    }

    @RequestMapping(value = "/{profileId}", method = RequestMethod.PATCH)
    public ProfileResponse updateProfile(@PathVariable Integer profileId, @RequestBody ProfileRequest profileRequest) {
        return new ProfileResponse(profileService.update(profileId, profileRequest));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/{profileId}", method = RequestMethod.DELETE)
    public void deleteProfile(@PathVariable Integer profileId) {
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