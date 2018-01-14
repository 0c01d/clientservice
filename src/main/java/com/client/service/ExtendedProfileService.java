package com.client.service;


import com.client.domain.ExtendedProfile;
import com.client.web.model.ExtendedProfileRequest;

import javax.persistence.EntityNotFoundException;

public interface ExtendedProfileService {
    ExtendedProfile getById(Long extendedProfileId) throws EntityNotFoundException;
    ExtendedProfile save(ExtendedProfileRequest extendedProfileRequest);
    ExtendedProfile update(Long extendedProfileId, ExtendedProfileRequest extendedProfileRequest);
}
