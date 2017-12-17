package com.client.service;

import com.client.domain.Profile;
import com.client.web.model.ProfileRequest;
import org.springframework.data.domain.Page;

public interface ProfileService {
//    Page<Profile> getProfiles(Integer page, Integer size);
    Profile getById(Integer profileId);
    Profile save(ProfileRequest profileRequest);
    Profile update(Integer profileId, ProfileRequest profileRequest);
    void deleteProfile(Integer profileId);

}