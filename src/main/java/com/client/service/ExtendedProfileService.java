package com.client.service;


import com.client.domain.ExtendedProfile;
import com.client.web.model.ExtendedProfileRequest;

public interface ExtendedProfileService {
    ExtendedProfile getById(Integer extendedProfileId);
    ExtendedProfile save(ExtendedProfileRequest extendedProfileRequest);
    ExtendedProfile update(Integer extendedProfileId, ExtendedProfileRequest extendedProfileRequest);
}
