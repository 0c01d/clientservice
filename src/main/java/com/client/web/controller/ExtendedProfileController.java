package com.client.web.controller;

import com.client.domain.ExtendedProfile;
import com.client.service.ExtendedProfileService;
import com.client.web.model.ExtendedProfileRequest;
import com.client.web.model.ExtendedProfileResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/profile/extended")
public class ExtendedProfileController {

    private final ExtendedProfileService extendedProfileService;

    @Autowired
    public ExtendedProfileController(ExtendedProfileService extendedProfileService) {
        this.extendedProfileService = extendedProfileService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public ExtendedProfileResponse createExtendedProfile(@Valid @RequestBody ExtendedProfileRequest extendedProfileRequest, HttpServletResponse response) {
        ExtendedProfile extendedProfile = extendedProfileService.save(extendedProfileRequest);
        response.addHeader(HttpHeaders.LOCATION, "/profile/extended/" + extendedProfile.getId());
        return new ExtendedProfileResponse(extendedProfile);
    }

    @RequestMapping(value = "/{profileId}", method = RequestMethod.GET)
    public ExtendedProfileResponse getExtendedProfileById(@PathVariable("profileId") final Integer profileId) {
        return new ExtendedProfileResponse(extendedProfileService.getById(profileId));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/{profileId}", method = RequestMethod.PATCH)
    public ExtendedProfileResponse updateExtendedProfile(@PathVariable Integer profileId,@Valid @RequestBody ExtendedProfileRequest extendedProfileRequest) {
        return new ExtendedProfileResponse(extendedProfileService.update(profileId, extendedProfileRequest));
    }
}
