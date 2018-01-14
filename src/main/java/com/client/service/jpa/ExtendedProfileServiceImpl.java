package com.client.service.jpa;

import com.client.domain.ExtendedProfile;
import com.client.domain.Profile;
import com.client.repository.ExtendedProfileRepository;
import com.client.service.ExtendedProfileService;
import com.client.web.model.ExtendedProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ExtendedProfileServiceImpl implements ExtendedProfileService {

    private final ExtendedProfileRepository extendedProfileRepository;

    private final ProfileServiceImpl profileService;

    @Autowired
    public ExtendedProfileServiceImpl(ExtendedProfileRepository extendedProfileRepository, ProfileServiceImpl profileService/*, AccountServiceImpl accountService*/) {
        this.extendedProfileRepository = extendedProfileRepository;
        this.profileService = profileService;
    }

    @Override
    @Transactional
    public ExtendedProfile save(ExtendedProfileRequest extendedProfileRequest) {
        ExtendedProfile extendedProfile = new ExtendedProfile()
                .setFirstname(extendedProfileRequest.getFirstname())
                .setMiddlename(extendedProfileRequest.getMiddlename())
                .setLastname(extendedProfileRequest.getLastname())
                .setGender(extendedProfileRequest.getGender())
                .setDateOfBirth(extendedProfileRequest.getDateOfBirth());

        Profile profile = profileService.getById(extendedProfileRequest.getProfileId());
        if( profile != null) {
            extendedProfile.setProfile(profile);
        }
        return extendedProfileRepository.save(extendedProfile);
    }

    @Override
    @Transactional(readOnly = true)
    public ExtendedProfile getById(Integer profileId) {
        return extendedProfileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("ExtendedProfile '{" + profileId + "}' not found"));
    }

    @Override
    @Transactional
    public ExtendedProfile update(Integer profileId, ExtendedProfileRequest extendedProfileRequest) {
        ExtendedProfile extendedProfile = this.getById(profileId);
        if (extendedProfile == null) {
            throw new EntityNotFoundException("ExtendedProfile '{" + profileId + "}' not found");
        }
        extendedProfile.setFirstname(extendedProfileRequest.getFirstname() != null ? extendedProfileRequest.getFirstname() : extendedProfile.getFirstname());
        extendedProfile.setMiddlename(extendedProfileRequest.getMiddlename() != null ? extendedProfileRequest.getMiddlename() : extendedProfile.getMiddlename());
        extendedProfile.setLastname(extendedProfileRequest.getLastname() != null ? extendedProfileRequest.getLastname() : extendedProfile.getLastname());
        extendedProfile.setGender(extendedProfileRequest.getGender() != null ? extendedProfileRequest.getGender() : extendedProfile.getGender());
        extendedProfile.setDateOfBirth(extendedProfileRequest.getDateOfBirth() != null ? extendedProfileRequest.getDateOfBirth() : extendedProfile.getDateOfBirth());
        return extendedProfileRepository.save(extendedProfile);
    }

}
