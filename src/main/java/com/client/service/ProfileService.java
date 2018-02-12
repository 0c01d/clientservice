package com.client.service;

import com.client.domain.Profile;
import com.client.web.model.ProfileRequest;
import org.springframework.data.domain.Page;

import javax.persistence.EntityNotFoundException;

public interface ProfileService {
//    Page<Profile> getProfiles(Integer page, Integer size);
    Profile getById(Long profileId) throws EntityNotFoundException;
    Profile getByUsername(String username) throws EntityNotFoundException;
    Profile save(ProfileRequest profileRequest);
    Profile update(Long profileId, ProfileRequest profileRequest);
    void deleteProfile(Long profileId);

}