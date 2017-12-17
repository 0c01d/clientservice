package com.client.service.jpa;

import com.client.domain.Profile;
import com.client.repository.ProfileRepository;
import com.client.service.ProfileService;
import com.client.web.model.ProfileRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository/*, AccountServiceImpl accountService*/) {
        this.profileRepository = profileRepository;
    }

   /* @Override
    @Transactional(readOnly = true)
    public Page<Profile> getProfiles(Integer page, Integer size) {
        if(page == null)
            page = 0;
        if(size == null)
            size = 5;
        return profileRepository.findAll(PageRequest.of(page, size));
    }*/

    @Override
    @Transactional
    public Profile save(ProfileRequest profileRequest) {
        Profile profile = new Profile()
                .setFirstname(profileRequest.getFirstname())
                .setMiddlename(profileRequest.getMiddlename())
                .setLastname(profileRequest.getLastname())
                .setEmail(profileRequest.getEmail())
                .setPhone(profileRequest.getPhone())
                .setGender(profileRequest.getGender())
                .setDateOfBirth(profileRequest.getDateOfBirth());
        return profileRepository.save(profile);
    }

    @Override
    @Transactional(readOnly = true)
    public Profile getById(Integer profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new EntityNotFoundException("Profile '{" + profileId + "}' not found"));
    }


    @Override
    @Transactional
    public Profile update(Integer profileId, ProfileRequest profileRequest) {
        Profile profile = this.getById(profileId);
        if (profile == null) {
            throw new EntityNotFoundException("Profile '{" + profileId + "}' not found");
        }
        profile.setFirstname(profileRequest.getFirstname() != null ? profileRequest.getFirstname() : profile.getFirstname());
        profile.setMiddlename(profileRequest.getMiddlename() != null ? profileRequest.getMiddlename() : profile.getMiddlename());
        profile.setLastname(profileRequest.getLastname() != null ? profileRequest.getLastname() : profile.getLastname());
        profile.setEmail(profileRequest.getEmail() != null ? profileRequest.getEmail() : profile.getEmail());
        profile.setPhone(profileRequest.getPhone() != null ? profileRequest.getPhone() : profile.getPhone());
        profile.setGender(profileRequest.getGender() != null ? profileRequest.getGender() : profile.getGender());
        profile.setDateOfBirth(profileRequest.getDateOfBirth() != null ? profileRequest.getDateOfBirth() : profile.getDateOfBirth());
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void deleteProfile(Integer profileId) {
        profileRepository.deleteById(profileId);
    }

}